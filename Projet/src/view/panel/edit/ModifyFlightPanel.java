package view.panel.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import exception.dataBase.ModifyException;
import model.Flight;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyFlightPanel extends JPanel {
    private ApplicationController controller;
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;

    public ModifyFlightPanel() throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        setModifyFlightForm(new ModifyFlightForm(flightForm));
        this.setLayout(new BorderLayout());
        this.add(modifyFlightForm, BorderLayout.PAGE_START);
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
            Flight flight = null;
            try {
                flight = flightForm.getFlight();
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
            String originalFlightNumber = modifyFlightForm.getFlightComboBoxID();
            try {
                if (flight.getNumber().equals(originalFlightNumber) || !flightForm.checkFlightNumberIsExisting()) {
                    controller.modifyFlight(flight, originalFlightNumber);
                    String flightToString = controller.getFlightToString(flight.getNumber());
                    try {
                        modifyFlightForm.updateFlightComboBox();
                    } catch (DataBaseConnectionException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
                    } catch (AllDataException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    modifyFlightForm.getFlightComboBox().setSelectedItem(flightToString);
                    JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ModifyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

