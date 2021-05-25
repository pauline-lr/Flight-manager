package view.panel.edit;

import controller.ApplicationController;
import exception.dataBase.*;
import model.Flight;
import view.CheckEmptyResult;
import view.form.edit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class ModifyFlightPanel extends JPanel {
    private ApplicationController controller;
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;

    public ModifyFlightPanel() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        setModifyFlightForm(new ModifyFlightForm(flightForm));
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        initializeValidationButton();
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setModifyFlightForm(ModifyFlightForm modifyFlightForm) {
        this.modifyFlightForm = modifyFlightForm;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private void initializeValidationButton() {
        JPanel buttonPanel = new JPanel();
        JButton validationButton = new JButton("Modifier");

        validationButton.addActionListener(new ValidationButtonListener());
        buttonPanel.add(validationButton, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Flight flight = flightForm.getFlight();
            String originalFlightNumber = modifyFlightForm.getFlightComboBoxID();
            try {
                if (flight.getNumber().equals(originalFlightNumber) || !flightForm.checkFlightNumberIsExisting()) {
                    controller.modifyFlight(flight, originalFlightNumber);
                    String flightToString = controller.getFlightToString(flight.getNumber());
                    modifyFlightForm.updateFlightComboBox();
                    modifyFlightForm.getFlightComboBox().setSelectedItem(flightToString);
                    JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}

