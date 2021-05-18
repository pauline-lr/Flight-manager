package view.panels.menuBarPanels.optionsFlightPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import model.Flight;
import view.table.ListAllFlightsTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteFlightPanel extends JPanel {
    private ApplicationController controller;
    private ListAllFlightsTable tableFlights;
    private JTable table;
    private JButton validateDeletationButton;

    public DeleteFlightPanel(MenuWindow menuWindow, ApplicationController controller) {
        this.controller = controller;
        this.setLayout(new BorderLayout());
        this.add(new DeleteFlightMessagePanel(), BorderLayout.PAGE_START);

        this.tableFlights = new ListAllFlightsTable(controller);
        this.table = new JTable(tableFlights);
        table.setModel(tableFlights);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        validateDeletationButton = new JButton("Supprimer");
        validateDeletationButton.addActionListener(new ValidationDeletation());

        this.add(new JScrollPane(table),BorderLayout.CENTER);
        this.add(validateDeletationButton, BorderLayout.SOUTH);
    }

    public static class DeleteFlightMessagePanel extends JPanel {
        private JLabel text;
        public DeleteFlightMessagePanel(){
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
            if(table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                tableFlights.removeRow(selectedRow);
                try {
                    Flight flight = tableFlights.getFlight(selectedRow);
                    controller.deleteFlight(flight.getNumber());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "Vol supprimé", "Succès", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner une ligne", "Succès", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
