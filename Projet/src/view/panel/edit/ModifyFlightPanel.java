package view.panel.edit;

import controller.ApplicationController;
import exception.*;
import model.Flight;
import view.form.edit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyFlightPanel extends JPanel {
    private ApplicationController controller;
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;
    private JButton validationButton;

    public ModifyFlightPanel() throws ConnectionException, RetrievalException, FlightException.NumberFlightException, FlightException.DepartureDateException, FlightException.ArrivalDateException {
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

    public JButton getValidationButton() {
        return validationButton;
    }

    private void initializeValidationButton() {
        JPanel buttonPanel = new JPanel();
        validationButton = new JButton("Modifier");

        validationButton.addActionListener(new ValidationButtonListener());
        buttonPanel.add(validationButton, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                Flight flight = flightForm.getFlight();
                if (flightForm.getFlight() != null) {
                    String originalFlightNumber = modifyFlightForm.getFlightComboBoxID();
                    if (flight.getNumber().equals(originalFlightNumber) || !flightForm.checkFlightNumberIsExisting()) {
                        controller.modifyFlight(flight, originalFlightNumber);
                        String flightToString = controller.getFlightToString(flight.getNumber());
                        modifyFlightForm.updateFlightComboBox();
                        modifyFlightForm.getFlightComboBox().setSelectedItem(flightToString);
                        JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (NotMatchException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (TextLengthException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.DepartureDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ModifyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (RetrievalException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.ArrivalDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
