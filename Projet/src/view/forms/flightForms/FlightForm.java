package view.forms.flightForms;

import controller.*;
import exception.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.*;

public class FlightForm extends JPanel {
    private static final int LENGTH_PILOT_ID = 7;
    private static final int LENGTH_AIRPORT_ID = 3;

    private ApplicationController controller = new ApplicationController();
    private Font font = new Font(null, Font.BOLD, 13);
    private JTextField numberTextField, mealDescriptionTextField;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel  departureLabel, arrivalLabel, numberLabel, mealLabel, departureDateLabel, departureHourLabel, arrivalDateLabel, arrivalHourLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel, empty;
    private JSpinner departureDay, departureMonth, departureYear, departureHour, departureMinute;
    private JSpinner arrivalDay, arrivalMonth, arrivalYear, arrivalHour, arrivalMinute;
    private SpinnerNumberModel departureDayModel, departureMonthModel, departureYearModel, departureHourModel, departureMinuteModel;
    private SpinnerNumberModel arrivalDayModel, arrivalMonthModel, arrivalYearModel, arrivalHourModel, arrivalMinuteModel;
    private JComboBox departureGateComboBox;
    private JComboBox arrivalGateComboBox;
    private JComboBox planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;
    private JComboBox departureTerminalComboBox, arrivalTerminalComboBox;
    private GregorianCalendar currentDate;

    public FlightForm() throws SQLException, DataBaseConnectionException {
        currentDate = new GregorianCalendar();
        setLayout(new GridLayout(16, 4));
        createFlightForm();
    }

    public void createFlightForm() throws SQLException, DataBaseConnectionException {
        addFlightNumberField();
        addPlaneField();
        addPilotField();
        addDepartureMomentField();
        addDepartureLocationField();
        addArrivalMomentField();
        addArrivalLocationField();
        addMealOnBoardField();
    }

    public Flight getFlight() throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        GregorianCalendar departureDate = new GregorianCalendar(
                Integer.parseInt(departureYear.getValue().toString()),
                Integer.parseInt(departureMonth.getValue().toString()) - 1,
                Integer.parseInt(departureDay.getValue().toString()),
                Integer.parseInt(departureHour.getValue().toString()),
                Integer.parseInt(departureMinute.getValue().toString())
        );

        /*GregorianCalendar(int year, int month, int dayOfMonth,
        int hourOfDay, int minute)*/
        GregorianCalendar arrivalDate = new GregorianCalendar(
                Integer.parseInt(arrivalYear.getValue().toString()),
                Integer.parseInt(arrivalMonth.getValue().toString()) - 1,
                Integer.parseInt(arrivalDay.getValue().toString()),
                Integer.parseInt(arrivalHour.getValue().toString()),
                Integer.parseInt(arrivalMinute.getValue().toString())
        );
        return new Flight(
                numberTextField.getText(),
                departureDate,
                arrivalDate,
                isMealOnBoardCheckBox.isSelected(),
                mealDescriptionTextField.getText(),
                getPilotId(),
                getDepartureGateId(),
                getArrivalGateId(),
                getPlaneId()
        );
    }

    //region Fields
    private void addFlightNumberField() {
        numberLabel = new JLabel("    Numéro");
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

    private void addPlaneField() {
        planeLabel = new JLabel("    Avion");
        planeLabel.setFont(font);
        planeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(planeLabel);
        try {
            planeComboBox = new JComboBox(controller.getAllPlanesForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(planeComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addPilotField() {
        pilotLabel = new JLabel("    Pilote");
        pilotLabel.setFont(font);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(pilotLabel);
        try {
            pilotComboBox = new JComboBox(controller.getAllPilotsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(pilotComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addDepartureMomentField() {
        // departure
        departureLabel = new JLabel("    Départ");
        departureLabel.setFont(font);
        departureLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(departureLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // departureDate
        departureDateLabel = new JLabel("Date : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        //void flightDate(SpinnerNumberModel day, SpinnerNumberModel month, SpinnerNumberModel year, SpinnerNumberModel hour, SpinnerNumberModel minute)
        departureDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH),1,31,1);
        departureDay = new JSpinner(departureDayModel);
        add(departureDay);

        departureMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH)+1,1,12,1);
        departureMonth = new JSpinner(departureMonthModel);
        add(departureMonth);

        departureYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR),2021,2023,1);
        departureYear = new JSpinner(departureYearModel);
        add(departureYear);

        addEmptyField();

        // departureTime
        departureHourLabel = new JLabel("Heure : ");
        departureHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureHourLabel);

        departureHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY),0,23,1);
        departureHour = new JSpinner(departureHourModel);
        add(departureHour);

        departureMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE),0,59,1);
        departureMinute = new JSpinner(departureMinuteModel);
        add(departureMinute);

        addEmptyField();
        addEmptyField();
    }
    private void addDepartureLocationField() throws SQLException, DataBaseConnectionException {
        // departureAirport
        departureAirportLabel = new JLabel("Aéroport : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);
        try {
            departureAirportComboBox = new JComboBox(controller.getAllAirportsForComboBox());
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
        departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalComboBox = new JComboBox(controller.getAllTerminalsOfAnAirportForComboBox(getDepartureAirportId()));
        departureTerminalComboBox.addItemListener(new departureTerminalComboBoxListener());
        this.add(departureTerminalComboBox);

        // departureGate
        departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        departureGateComboBox = new JComboBox(controller.getAllGatesOfAnAirportAndTerminalForComboBox(getDepartureAirportId(), (String)departureTerminalComboBox.getSelectedItem()));
        this.add(departureGateComboBox);
    }

    private void addArrivalMomentField() {
        // arrival
        arrivalLabel = new JLabel("    Arrivée");
        arrivalLabel.setFont(font);
        arrivalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(arrivalLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // arrivalDate
        arrivalDateLabel = new JLabel("Date : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);

        arrivalDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH),1,31,1);
        arrivalDay = new JSpinner(arrivalDayModel);
        add(arrivalDay);

        arrivalMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH)+1,1,12,1);
        arrivalMonth = new JSpinner(arrivalMonthModel);
        add(arrivalMonth);

        arrivalYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR),2021,2023,1);
        arrivalYear = new JSpinner(arrivalYearModel);
        add(arrivalYear);

        addEmptyField();

        // arrivalTime
        arrivalHourLabel = new JLabel("Heure : ");
        arrivalHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalHourLabel);

        arrivalHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY),0,23,1);
        arrivalHour = new JSpinner(arrivalHourModel);
        add(arrivalHour);

        arrivalMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE),0,59,1);
        arrivalMinute = new JSpinner(arrivalMinuteModel);
        add(arrivalMinute);

        addEmptyField();
        addEmptyField();
    }
    private void addArrivalLocationField() throws SQLException, DataBaseConnectionException {
        // arrivalAirport
        arrivalAirportLabel = new JLabel("Aéroport : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);
        try {
            arrivalAirportComboBox = new JComboBox(controller.getAllAirportsForComboBox());
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
        arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalComboBox = new JComboBox(controller.getAllTerminalsOfAnAirportForComboBox(getArrivalAirportId()));
        arrivalTerminalComboBox.addItemListener(new arrivalTerminalComboBoxListener());
        this.add(arrivalTerminalComboBox);

        // arrivalGate
        arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);

        arrivalGateComboBox = new JComboBox(controller.getAllGatesOfAnAirportAndTerminalForComboBox(getArrivalAirportId(), (String)arrivalTerminalComboBox.getSelectedItem()));
        this.add(arrivalGateComboBox);
    }

    private void addMealOnBoardField() {
        mealLabel = new JLabel("    Repas");
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

        mealDescriptionLabel = new JLabel("Description : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextField = new JTextField();
        mealDescriptionTextField.setEnabled(false);
        mealDescriptionTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealDescriptionTextField);
    }

    private void addEmptyField(){
        empty = new JLabel();
        empty.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(empty);
    }
    //endregion

    //region ID Getters
    public String getId(String text) {
        Pattern pattern = Pattern.compile("^\\w+(?=\\s-\\s)");
        Matcher matcher = pattern.matcher(text);
        String id = null;
        if (matcher.find())
        {
            id = matcher.group();
        }
        return id;
    }

    public String getDepartureAirportId() {
        String departureAirportText = (String)departureAirportComboBox.getSelectedItem();
        return getId(departureAirportText);
    }
    public String getDepartureGateId() {
        return (String) departureTerminalComboBox.getSelectedItem() + departureGateComboBox.getSelectedItem() + getDepartureAirportId();
    }

    public String getArrivalAirportId() {
        String arrivalAirportText = (String)arrivalAirportComboBox.getSelectedItem();
        return getId(arrivalAirportText);
    }
    public String getArrivalGateId() {
        return (String) arrivalTerminalComboBox.getSelectedItem() + arrivalGateComboBox.getSelectedItem() + getArrivalAirportId();
    }

    public Integer getPlaneId() {
        String planeText = (String) planeComboBox.getSelectedItem();
        return Integer.parseInt(getId(planeText));
    }

    public String getPilotId() {
        String pilotText = (String)pilotComboBox.getSelectedItem();
        return getId(pilotText);
    }
    //endregion

    //region Listeners
    private class departureAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                try {
                    String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(getDepartureAirportId());
                    String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(getDepartureAirportId(), (String) departureTerminalComboBox.getSelectedItem());
                    departureTerminalComboBox.removeAllItems();
                    departureGateComboBox.removeAllItems();
                    for (String terminal : updatedTerminalsOfAnAirportForComboBox) {
                        departureTerminalComboBox.addItem(terminal);
                    }
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    private class departureTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                try {
                    String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(getDepartureAirportId(), (String) departureTerminalComboBox.getSelectedItem());
                    departureGateComboBox.removeAllItems();
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private class arrivalAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                try {
                    String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(getArrivalAirportId());
                    String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(getArrivalAirportId(), (String) arrivalTerminalComboBox.getSelectedItem());
                    arrivalTerminalComboBox.removeAllItems();
                    arrivalGateComboBox.removeAllItems();
                    for (String terminal : updatedTerminalsOfAnAirportForComboBox) {
                        arrivalTerminalComboBox.addItem(terminal);
                    }
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        arrivalGateComboBox.addItem(gate);
                    }
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    private class arrivalTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                try {
                    String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(getArrivalAirportId(), (String) arrivalTerminalComboBox.getSelectedItem());
                    arrivalGateComboBox.removeAllItems();
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        arrivalGateComboBox.addItem(gate);
                    }
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    private class MealOnBoardListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent){
            mealDescriptionTextField.setEnabled(itemEvent.getStateChange() == ItemEvent.SELECTED);
        }
    }
    //endregion
}
