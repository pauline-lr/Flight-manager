package view.panel.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import exception.dataBase.ModifyException;
import view.table.AllFlightsListTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteFlightPanel extends JPanel {
    private ApplicationController controller;
    private AllFlightsListTable flightsTable;
    private JTable table;
    private JButton validateDeletionButton;

    public DeleteFlightPanel() throws DataBaseConnectionException {
        setController(new ApplicationController());
        setFlightsTable(new AllFlightsListTable());
        this.setLayout(new BorderLayout());
        this.add(new DeleteFlightMessagePanel(), BorderLayout.PAGE_START);

        this.table = new JTable(flightsTable);
        table.setModel(flightsTable);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        validateDeletionButton = new JButton("Supprimer");
        validateDeletionButton.addActionListener(new ValidationDeletion());

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(validateDeletionButton, BorderLayout.CENTER);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void setFlightsTable(AllFlightsListTable flightsTable) {
        this.flightsTable = flightsTable;
    }

    public AllFlightsListTable getFlightsTable() {
        return flightsTable;
    }

    public JButton getValidateDeletionButton() {
        return validateDeletionButton;
    }

    public static class DeleteFlightMessagePanel extends JPanel {

        public DeleteFlightMessagePanel() {
            this.setLayout(new GridLayout(15, 1, 3, 3));

            JLabel text = new JLabel("<html>" +
                    "<center>Sélectionnez le(s) vol(s) à supprimer</center>" +
                    "</html>");
            this.setLayout(new FlowLayout());
            this.add(text);
        }
    }

    private class ValidationDeletion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (table.getSelectedRow() != -1) {
                int choice = getConfirmation("Êtes-vous sûr de vouloir supprimer ce(s) vol(s) ?\nLes réservations liées seront également supprimées.");

                if (choice == 0) {
                    int[] selectedRows = table.getSelectedRows();
                    try {
                        for (int selectedRow : selectedRows) {
                            controller.deleteFlight(flightsTable.getFlight(selectedRow));
                        }
                        for (int i = 0; i < selectedRows.length; i++) {
                            flightsTable.removeRow(selectedRows[i] - i);
                        }
                    } catch (ModifyException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    } catch (DataBaseConnectionException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    // ICIIIIII
                    try {
                        if (controller.getAllFlights().isEmpty()) {
                            validateDeletionButton.setEnabled(false);
                        }
                    } catch (AllDataException e) {
                        e.printStackTrace();
                    } catch (DataBaseConnectionException e) {
                        e.printStackTrace();
                    } catch (FlightException.NumberFlightException e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Vol(s) supprimé(s)", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } else { // cancel == 2     exit == -1
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int getConfirmation(String theMessage) {
        return JOptionPane.showConfirmDialog(null, theMessage, "Alert", JOptionPane.OK_CANCEL_OPTION);
    }
}
