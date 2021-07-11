package view.form.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import model.Flight;
import tool.Format;
import tool.GetID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ModifyFlightForm extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;
    private JComboBox<String> flightComboBox;
    private JLabel flightLabel;

    public ModifyFlightForm(FlightForm flightForm) throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        setFlightForm(flightForm);
        this.setLayout(new GridLayout(2, 1, 3, 3));

        flightLabel = new JLabel("    Vol à modifier");
        flightLabel.setFont(Format.titleFont);
        flightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightLabel);
        if (controller.getAllFlightsToString().length > 0) {
            flightComboBox = new JComboBox<>(controller.getAllFlightsToString());
            updateFormInformation();
            flightComboBox.addItemListener(new flightComboBoxListener());
        } else {
            JOptionPane.showMessageDialog(null, "Aucun vol à modifier", "Informations", JOptionPane.INFORMATION_MESSAGE);
        }
        this.add(flightComboBox);
    }

    public JComboBox<String> getFlightComboBox() {
        return flightComboBox;
    }

    public void updateFlightComboBox() throws DataBaseConnectionException, AllDataException {
        flightComboBox.removeAllItems();
        for (String flight : controller.getAllFlightsToString()) {
            flightComboBox.addItem(flight);
        }
    }

    public String getFlightComboBoxID() {
        return GetID.getFlightID(flightComboBox);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private class flightComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateFormInformation();
            }
        }
    }

    private void updateFormInformation() {
        String flightNumber = getFlightComboBoxID();
        try {
            Flight flight = controller.getFlight(flightNumber);
            flightForm.setFlightNumberComboBox(flight.getNumber());
            flightForm.setPilotComboBox(flight.getPilot());
            flightForm.setPlaneComboBox(flight.getNumberPlane());
            flightForm.setDepartureDate(flight.getDepartureTime());
            flightForm.setDepartureTime(flight.getDepartureTime());
            flightForm.setArrivalDate(flight.getArrivalTime());
            flightForm.setArrivalTime(flight.getArrivalTime());
            flightForm.setDepartureAirportComboBox(flight.getDepartureGate());
            flightForm.setDepartureTerminalComboBox(flight.getDepartureGate());
            flightForm.setDepartureGateComboBox(flight.getDepartureGate());
            flightForm.setArrivalAirportComboBox(flight.getArrivalGate());
            flightForm.setArrivalTerminalComboBox(flight.getArrivalGate());
            flightForm.setArrivalGateComboBox(flight.getArrivalGate());
            flightForm.setIsMealOnBoardCheckBox(flight.getMealOnBoard());
            flightForm.setMealDescriptionTextArea(flight.getMealDescription());
        } catch (DataBaseConnectionException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (AllDataException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (FlightException.NumberFlightException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
