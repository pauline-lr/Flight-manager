package view.panel.edit;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import exception.FlightException;
import view.CheckEmptyResult;
import view.table.AllFlightsListTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteFlightPanel extends JPanel {
    private ApplicationController controller;
    private AllFlightsListTable flightsTable;
    private JTable table;
    private JButton validateDeletationButton;

    public DeleteFlightPanel() throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        setController(new ApplicationController());
        setFlightsTable(new AllFlightsListTable());
        this.setLayout(new BorderLayout());
        this.add(new DeleteFlightMessagePanel(), BorderLayout.PAGE_START);

        this.table = new JTable(flightsTable);
        table.setModel(flightsTable);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        validateDeletationButton = new JButton("Supprimer");
        validateDeletationButton.addActionListener(new ValidationDeletation());

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        CheckEmptyResult.checkResultIsEmpty(flightsTable.getFlights());
        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(validateDeletationButton, BorderLayout.CENTER);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void setFlightsTable(AllFlightsListTable flightsTable) {
        this.flightsTable = flightsTable;
    }

    public static class DeleteFlightMessagePanel extends JPanel {
        private JLabel text;

        public DeleteFlightMessagePanel() {
            this.setLayout(new GridLayout(15, 1, 3, 3));

            text = new JLabel("<html>" +
                    "<center>Sélectionnez le(s) vol(s) à supprimer</center>" +
                    "</html>");
            this.setLayout(new FlowLayout());
            this.add(text);
        }
    }

    private class ValidationDeletation implements ActionListener {
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
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (DataBaseConnectionException throwables) {
                        throwables.printStackTrace();
                        JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null, "Vol(s) supprimé(s)", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } else { // cancel == 2     exit == -1
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int getConfirmation(String theMessage) {
        return JOptionPane.showConfirmDialog((Component) null, theMessage, "Alert", JOptionPane.OK_CANCEL_OPTION);
    }
}
