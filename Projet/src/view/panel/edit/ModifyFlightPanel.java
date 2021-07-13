package view.panel.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.NotMatchException;
import exception.TextLengthException;
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
            try {
                if (flightForm.getFlight() != null) {
                    Flight flight = flightForm.getFlight();
                    String originalFlightNumber = modifyFlightForm.getFlightComboBoxID();
                    if (flight.getNumber().equals(originalFlightNumber) || !flightForm.checkFlightNumberIsExisting()) {
                        controller.modifyFlight(flight, originalFlightNumber);
                        String flightToString = controller.getFlightToString(flight.getNumber());
                        modifyFlightForm.getFlightComboBox().setSelectedItem(flightToString);
                        JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NotMatchException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (TextLengthException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.DepartureDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ModifyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
