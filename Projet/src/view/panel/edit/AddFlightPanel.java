package view.panel.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.NotMatchException;
import exception.TextLengthException;
import exception.AddException;
import exception.RetrievalException;
import exception.ConnectionException;
import exception.ModifyException;
import model.Flight;
import tool.GetID;
import view.form.edit.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddFlightPanel extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;

    public AddFlightPanel() throws ConnectionException, RetrievalException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        this.setLayout(new BorderLayout());
        this.add(new AddFlightForm(), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);
        flightForm.getDepartureDate().addChangeListener(new departureMomentComboBoxListener());
        flightForm.getDepartureTime().addChangeListener(new departureMomentComboBoxListener());
        flightForm.getDepartureAirportComboBox().addItemListener(new departureAirportComboBoxListener());
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
            try {
                if ((flightForm.getFlight() != null) && !(flightForm.checkFlightNumberIsExisting())) {
                        Flight flight = flightForm.getFlight();
                        controller.addFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (AddException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ModifyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.DepartureDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (TextLengthException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (NotMatchException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.ArrivalDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class departureMomentComboBoxListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent event) {
            ArrayList<String> updatedPilotForComboBox = new ArrayList<>();
            ArrayList<String> updatedPlaneForComboBox = new ArrayList<>();
            try {
                updatedPilotForComboBox = controller.getPilotsInOrder(flightForm.getDepartureMoment(), GetID.getAirportID(flightForm.getDepartureAirportComboBox()));
            } catch (ConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (RetrievalException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            try {
                updatedPlaneForComboBox = controller.getAllAvailablePlanesToString(flightForm.getDepartureMoment());
            } catch (RetrievalException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            flightForm.getPilotComboBox().removeAllItems();
            flightForm.getPlaneComboBox().removeAllItems();
            for (String pilot : updatedPilotForComboBox) {
                flightForm.getPilotComboBox().addItem(pilot);
            }
            for (String plane : updatedPlaneForComboBox) {
                flightForm.getPlaneComboBox().addItem(plane);
            }
        }
    }

    private class departureAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            ArrayList<String> updatedPilotForComboBox = new ArrayList<>();
            try {
                updatedPilotForComboBox = controller.getPilotsInOrder(flightForm.getDepartureMoment(), GetID.getAirportID(flightForm.getDepartureAirportComboBox()));
            } catch (RetrievalException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            flightForm.getPilotComboBox().removeAllItems();
            if (event.getStateChange() == ItemEvent.SELECTED) {
                for (String pilot : updatedPilotForComboBox) {
                    flightForm.getPilotComboBox().addItem(pilot);
                }
            }
        }
    }
}
