package view.forms.flightForms;

import controller.*;
import exception.*;
import model.*;
import tool.GetID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class FlightForm extends JPanel {
    private final Font font = new Font(null, Font.BOLD, 13);
    private ApplicationController controller;
    private GregorianCalendar currentDate;

    private JTextField numberTextField, mealDescriptionTextField;
    private JCheckBox isMealOnBoardCheckBox;
    private JSpinner departureDay, departureMonth, departureYear, departureHour, departureMinute,
            arrivalDay, arrivalMonth, arrivalYear, arrivalHour, arrivalMinute;
    private SpinnerNumberModel departureDayModel, departureMonthModel, departureYearModel, departureHourModel, departureMinuteModel,
            arrivalDayModel, arrivalMonthModel, arrivalYearModel, arrivalHourModel, arrivalMinuteModel;
    private JComboBox pilotComboBox, planeComboBox,
            departureAirportComboBox, departureTerminalComboBox, departureGateComboBox,
            arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox;

    public FlightForm() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setCurrentDate(new GregorianCalendar());
        setLayout(new GridLayout(16, 4));
        createFlightForm();
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setCurrentDate(GregorianCalendar currentDate) {
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
        GregorianCalendar departureDate = new GregorianCalendar(
                (Integer) departureYear.getValue(),
                (Integer) departureMonth.getValue() - 1,
                (Integer) departureDay.getValue(),
                (Integer) departureHour.getValue(),
                (Integer) departureMinute.getValue()
        );

        GregorianCalendar arrivalDate = new GregorianCalendar(
                (Integer) arrivalYear.getValue(),
                (Integer) arrivalMonth.getValue() - 1,
                (Integer) arrivalDay.getValue(),
                (Integer) arrivalHour.getValue(),
                (Integer) arrivalMinute.getValue()
        );

        return new Flight(
                numberTextField.getText(),
                departureDate,
                arrivalDate,
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
        numberLabel.setFont(font);
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
        pilotLabel.setFont(font);
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
        planeLabel.setFont(font);
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
        departureLabel.setFont(font);
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

        //void flightDate(SpinnerNumberModel day, SpinnerNumberModel month, SpinnerNumberModel year, SpinnerNumberModel hour, SpinnerNumberModel minute)
        departureDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH), 1, 31, 1);
        departureDay = new JSpinner(departureDayModel);
        add(departureDay);

        departureMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH) + 1, 1, 12, 1);
        departureMonth = new JSpinner(departureMonthModel);
        add(departureMonth);

        departureYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR), 2021, 2023, 1);
        departureYear = new JSpinner(departureYearModel);
        add(departureYear);

        addEmptyField();

        // departureTime
        JLabel departureTimeLabel = new JLabel("Heure : ");
        departureTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTimeLabel);

        departureHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY), 0, 23, 1);
        departureHour = new JSpinner(departureHourModel);
        add(departureHour);

        departureMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE), 0, 59, 1);
        departureMinute = new JSpinner(departureMinuteModel);
        add(departureMinute);

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
        arrivalLabel.setFont(font);
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

        arrivalDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH), 1, 31, 1);
        arrivalDay = new JSpinner(arrivalDayModel);
        add(arrivalDay);

        arrivalMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH) + 1, 1, 12, 1);
        arrivalMonth = new JSpinner(arrivalMonthModel);
        add(arrivalMonth);

        arrivalYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR), 2021, 2023, 1);
        arrivalYear = new JSpinner(arrivalYearModel);
        add(arrivalYear);

        addEmptyField();

        // arrivalTime
        JLabel arrivalTimeLabel = new JLabel("Heure : ");
        arrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTimeLabel);

        arrivalHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY), 0, 23, 1);
        arrivalHour = new JSpinner(arrivalHourModel);
        add(arrivalHour);

        arrivalMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE), 0, 59, 1);
        arrivalMinute = new JSpinner(arrivalMinuteModel);
        add(arrivalMinute);

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
        mealLabel.setFont(font);
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
