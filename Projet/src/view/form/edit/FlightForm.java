package view.form.edit;

import controller.*;
import exception.*;
import exception.dataBase.DataBaseConnectionException;
import model.*;
import tool.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class FlightForm extends JPanel {
    private ApplicationController controller;
    private Date currentDate;
    private JTextField numberTextField, mealDescriptionTextField;
    private JCheckBox isMealOnBoardCheckBox;
    private JSpinner departureDate, departureTime, arrivalDate, arrivalTime;
    private JComboBox<String> pilotComboBox, planeComboBox,
            departureAirportComboBox, departureTerminalComboBox, departureGateComboBox,
            arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox;

    public FlightForm() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setCurrentDate(new Date());
        setLayout(new GridLayout(16, 4));
        createFlightForm();
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    private void createFlightForm() throws SQLException, DataBaseConnectionException {
        addFlightNumberField();
        addPilotField();
        addPlaneField();
        addDepartureMomentField();
        addDepartureLocationField();
        addArrivalMomentField();
        addArrivalLocationField();
        addMealOnBoardField();
    }

    public Flight getFlight() throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        GregorianCalendar departureDateGc = (GregorianCalendar) departureDate.getValue();
        GregorianCalendar departureTimeGc = (GregorianCalendar) departureTime.getValue();
        GregorianCalendar departureGc = new GregorianCalendar(departureDateGc.YEAR, departureDateGc.MONTH, departureDateGc.DAY_OF_MONTH, departureTimeGc.HOUR, departureTimeGc.MINUTE);

        GregorianCalendar arrivalDateGc = (GregorianCalendar) arrivalDate.getValue();
        GregorianCalendar arrivalTimeGc = (GregorianCalendar) arrivalTime.getValue();
        GregorianCalendar arrivalGc = new GregorianCalendar(arrivalDateGc.YEAR, arrivalDateGc.MONTH, arrivalDateGc.DAY_OF_MONTH, arrivalTimeGc.HOUR, arrivalTimeGc.MINUTE);

        return new Flight(
                numberTextField.getText(),
                departureGc,
                arrivalGc,
                isMealOnBoardCheckBox.isSelected(),
                mealDescriptionTextField.getText(),
                GetID.getPilotId(pilotComboBox),
                GetID.getGateId(departureAirportComboBox, departureTerminalComboBox, departureGateComboBox),
                GetID.getGateId(arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox),
                GetID.getPlaneId(planeComboBox)
        );
    }

    //region Fields
    private void addFlightNumberField() {
        JLabel numberLabel = new JLabel("    Numéro");
        numberLabel.setFont(Format.font);
        numberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(numberLabel);

        numberTextField = new JTextField();
        numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(numberTextField);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addPilotField() {
        JLabel pilotLabel = new JLabel("    Pilote");
        pilotLabel.setFont(Format.font);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(pilotLabel);

        try {
            pilotComboBox = new JComboBox<>(controller.getAllPilotsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(pilotComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addPlaneField() {
        JLabel planeLabel = new JLabel("    Avion");
        planeLabel.setFont(Format.font);
        planeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(planeLabel);

        try {
            planeComboBox = new JComboBox<>(controller.getAllPlanesForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(planeComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addDepartureMomentField() {
        // departure
        JLabel departureLabel = new JLabel("    Départ");
        departureLabel.setFont(Format.font);
        departureLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(departureLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // departureDate
        JLabel departureDateLabel = new JLabel("Date : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        departureDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureDate.setEditor(new JSpinner.DateEditor(departureDate, "dd/MM/yyyy"));
        this.add(departureDate); 

        addEmptyField();
        addEmptyField();
        
        // departureTime
        JLabel departureTimeLabel = new JLabel("Heure : ");
        departureTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTimeLabel);

        departureTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureTime.setEditor(new JSpinner.DateEditor(departureTime, "HH:mm"));
        this.add(departureTime);

        addEmptyField();
        addEmptyField();
    }

    private void addDepartureLocationField() throws SQLException, DataBaseConnectionException {
        // departureAirport
        JLabel departureAirportLabel = new JLabel("Aéroport : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);

        try {
            departureAirportComboBox = new JComboBox<>(controller.getAllAirportsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        departureAirportComboBox.addItemListener(new departureAirportComboBoxListener());
        this.add(departureAirportComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // departureTerminal
        JLabel departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportId(departureAirportComboBox)));
        departureTerminalComboBox.addItemListener(new departureTerminalComboBoxListener());
        this.add(departureTerminalComboBox);

        // departureGate
        JLabel departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        departureGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem()));
        this.add(departureGateComboBox);
    }

    private void addArrivalMomentField() {
        // arrival
        JLabel arrivalLabel = new JLabel("    Arrivée");
        arrivalLabel.setFont(Format.font);
        arrivalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(arrivalLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // arrivalDate
        JLabel arrivalDateLabel = new JLabel("Date : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);


        // arrivalDate
        arrivalDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        arrivalDate.setEditor(new JSpinner.DateEditor(arrivalDate, "dd/MM/yyyy"));
        this.add(arrivalDate);

        addEmptyField();
        addEmptyField();

        // arrivalTime
        JLabel arrivalTimeLabel = new JLabel("Heure : ");
        arrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTimeLabel);

        arrivalTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        arrivalTime.setEditor(new JSpinner.DateEditor(arrivalTime, "HH:mm"));
        this.add(arrivalTime);

        addEmptyField();
        addEmptyField();
    }

    private void addArrivalLocationField() throws SQLException, DataBaseConnectionException {
        // arrivalAirport
        JLabel arrivalAirportLabel = new JLabel("Aéroport : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);

        try {
            arrivalAirportComboBox = new JComboBox<>(controller.getAllAirportsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        arrivalAirportComboBox.addItemListener(new arrivalAirportComboBoxListener());
        this.add(arrivalAirportComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // arrivalTerminal
        JLabel arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportId(arrivalAirportComboBox)));
        arrivalTerminalComboBox.addItemListener(new arrivalTerminalComboBoxListener());
        this.add(arrivalTerminalComboBox);

        // arrivalGate
        JLabel arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);

        arrivalGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem()));
        this.add(arrivalGateComboBox);
    }

    private void addMealOnBoardField() {
        JLabel mealLabel = new JLabel("    Repas");
        mealLabel.setFont(Format.font);
        mealLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealLabel);
        addEmptyField();
        addEmptyField();
        addEmptyField();

        addEmptyField();
        addEmptyField();
        isMealOnBoardCheckBox = new JCheckBox("Repas à bord");
        isMealOnBoardCheckBox.addItemListener(new MealOnBoardListener());
        isMealOnBoardCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isMealOnBoardCheckBox);

        JLabel mealDescriptionLabel = new JLabel("Description : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextField = new JTextField();
        mealDescriptionTextField.setEnabled(false);
        mealDescriptionTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealDescriptionTextField);
    }

    private void addEmptyField() {
        JLabel empty = new JLabel();
        empty.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(empty);
    }
    //endregion

    //region Listeners
    private class departureAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportId(departureAirportComboBox));
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
                departureTerminalComboBox.removeAllItems();
                departureGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String terminal : updatedTerminalsOfAnAirportForComboBox) {
                        departureTerminalComboBox.addItem(terminal);
                    }
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class departureTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
                departureGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class arrivalAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportId(arrivalAirportComboBox));
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
                arrivalTerminalComboBox.removeAllItems();
                arrivalGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String terminal : updatedTerminalsOfAnAirportForComboBox) {
                        arrivalTerminalComboBox.addItem(terminal);
                    }
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        arrivalGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class arrivalTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportId(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
                arrivalGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        arrivalGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class MealOnBoardListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            mealDescriptionTextField.setEnabled(itemEvent.getStateChange() == ItemEvent.SELECTED);
        }
    }
    //endregion
}
