package view.form.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.dataBase.DataBaseConnectionException;
import model.Flight;
import tool.Format;
import tool.GetID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

public class ModifyFlightForm extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;
    private JComboBox<String> flightComboBox;
    private JLabel flightLabel;

    public ModifyFlightForm(FlightForm flightForm) throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setFlightForm(flightForm);
        this.setLayout(new GridLayout(2, 1, 3, 3));

        flightLabel = new JLabel("    Vol à modifier");
        flightLabel.setFont(Format.titleFont);
        flightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightLabel);

        flightComboBox = new JComboBox<>(controller.getAllFlightsForComboBox());
        updateFormInformation();
        flightComboBox.addItemListener(new flightComboBoxListener());
        this.add(flightComboBox);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public JComboBox<String> getFlightComboBox() {
        return flightComboBox;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private class flightComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            updateFormInformation();
        }
    }

    private void updateFormInformation() {
        String flightNumber = GetID.getFlightID(flightComboBox);
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
        } catch (FlightException.MealDescriptionException e) {
            e.printStackTrace();
        } catch (FlightException.NumberFlightException e) {
            e.printStackTrace();
        }
    }
}
