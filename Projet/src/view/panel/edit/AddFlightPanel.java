package view.panel.edit;

import controller.ApplicationController;
import exception.dataBase.*;
import model.Flight;
import view.form.edit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddFlightPanel extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;

    public AddFlightPanel() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        initializeValidationButton();
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private void initializeValidationButton() {
        JPanel buttonPanel = new JPanel();
        JButton validationButton = new JButton("Ajouter");

        validationButton.addActionListener(new ValidationButtonListener());
        buttonPanel.add(validationButton, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String flightNumberText = flightForm.getFlightNumberTextField().getText();
            try {
                if (!controller.flightNumberIsExisting(flightNumberText)) {
                    Flight flight = flightForm.getFlight();
                    controller.addFlight(flight);
                    JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ce numéro de vol existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
