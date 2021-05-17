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

    private JTextField numberTextField, mealDescriptionTextField;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel  numberLabel, departureDateLabel, departureHourLabel, arrivalDateLabel, arrivalHourLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel, empty;
    private JSpinner departureDay, departureMonth, departureYear, departureHour, departureMinute;
    private JSpinner arrivalDay, arrivalMonth, arrivalYear, arrivalHour, arrivalMinute;
    private SpinnerNumberModel departureDayModel, departureMonthModel, departureYearModel, departureHourModel, departureMinuteModel;
    private SpinnerNumberModel arrivalDayModel, arrivalMonthModel, arrivalYearModel, arrivalHourModel, arrivalMinuteModel;
    private JComboBox departureGateSpinner;
    private JComboBox arrivalGateSpinner;
    private JComboBox planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;
    private JComboBox departureTerminalComboBox, arrivalTerminalComboBox;
    private GregorianCalendar currentDate;

    public FlightForm() throws SQLException, DataBaseConnectionException {
        currentDate = new GregorianCalendar();
        setLayout(new GridLayout(13, 4));

        initForm();
    }

    public void initForm() throws SQLException, DataBaseConnectionException {
        numberLabel = new JLabel("N° de vol : ");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(numberLabel);

        numberTextField = new JTextField();
        numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(numberTextField);

        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();


        // departureDate
        departureDateLabel = new JLabel("Date de départ  : ");
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
        /*departureDay = new JComboBox();
        this.add(departureDay);
        departureMonth = new JComboBox();
        this.add(departureMonth);
        departureYear = new JComboBox();
        this.add(departureYear);*/

        addEmptyLabel();

        departureHourLabel = new JLabel("Heure de départ  : ");
        departureHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureHourLabel);

        departureHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY),0,23,1);
        departureHour = new JSpinner(departureHourModel);
        add(departureHour);

        departureMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE),0,59,1);
        departureMinute = new JSpinner(departureMinuteModel);
        add(departureMinute);

        /*departureHour = new JComboBox();
        this.add(departureHour);
        departureMinute = new JComboBox();
        this.add(departureMinute);*/

        addEmptyLabel();
        addEmptyLabel();

        // departureAirport
        departureAirportLabel = new JLabel("Aéroport de départ : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);
        try {
            departureAirportComboBox = new JComboBox(controller.getAllAirportsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(departureAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();


        // departureGate
        departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalComboBox = new JComboBox(controller.getAllTerminalsOfAnAirportForComboBox(getDepartureAirportId()));
        this.add(departureTerminalComboBox);

        departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        departureGateSpinner = new JComboBox(controller.getAllGatesOfAnAirportAndTerminalForComboBox(getDepartureAirportId(), (String)getDepartureTerminalComboBox().getSelectedItem()));
        /*
        try {
            //Pas d'accès à gate ? '
            //departureGateSpinner = new JComboBox(controller.);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        */
        this.add(departureGateSpinner);

        // arrivalDate
        arrivalDateLabel = new JLabel("Date d'arrivée : ");
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

        addEmptyLabel();

        arrivalHourLabel = new JLabel("Heure d'arrivée  : ");
        arrivalHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalHourLabel);

        arrivalHourModel = new SpinnerNumberModel(currentDate.get(Calendar.HOUR_OF_DAY),0,23,1);
        arrivalHour = new JSpinner(arrivalHourModel);
        add(arrivalHour);

        arrivalMinuteModel = new SpinnerNumberModel(currentDate.get(Calendar.MINUTE),0,59,1);
        arrivalMinute = new JSpinner(arrivalMinuteModel);
        add(arrivalMinute);

        addEmptyLabel();
        addEmptyLabel();


        // arrivalAirport
        arrivalAirportLabel = new JLabel("Aéroport d'arrivée : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);
        try {
            arrivalAirportComboBox = new JComboBox(controller.getAllAirportsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(arrivalAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();


        // arrivalGate
        arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalComboBox = new JComboBox(controller.getAllTerminalsOfAnAirportForComboBox(getArrivalAirportId()));
        this.add(arrivalTerminalComboBox);

        arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);


        arrivalGateSpinner = new JComboBox(controller.getAllGatesOfAnAirportAndTerminalForComboBox(getArrivalAirportId(), (String)getArrivalTerminalComboBox().getSelectedItem()));
        this.add(arrivalGateSpinner);

        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();
        addEmptyLabel();

        // plane
        planeLabel = new JLabel("Avion : ");
        planeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(planeLabel);
        try {
            planeComboBox = new JComboBox(controller.getAllPlanesForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(planeComboBox);

        // pilot
        pilotLabel = new JLabel("Pilote : ");
        pilotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(pilotLabel);
        try {
            pilotComboBox = new JComboBox(controller.getAllPilotsForComboBox());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(pilotComboBox);

        addEmptyLabel();


        // isMealOnBoard
        isMealOnBoardCheckBox = new JCheckBox("Repas à bord");
        isMealOnBoardCheckBox.addItemListener(new MealOnBoardListener());
        isMealOnBoardCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isMealOnBoardCheckBox);

        addEmptyLabel();
        // mealDescription
        mealDescriptionLabel = new JLabel("Description du repas : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextField = new JTextField();
        mealDescriptionTextField.setEnabled(false);
        mealDescriptionTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealDescriptionTextField);
    }

    private void addEmptyLabel(){
        empty = new JLabel();
        empty.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(empty);
    }

    public JTextField getMealDescriptionTextField() {
        return mealDescriptionTextField;
    }

    public JComboBox getDepartureTerminalComboBox() {
        return departureTerminalComboBox;
    }

    public JComboBox getArrivalTerminalComboBox() {
        return arrivalTerminalComboBox;
    }

    public JComboBox getDepartureGateSpinner() {
        return departureGateSpinner;
    }

    public JComboBox getArrivalGateSpinner() {
        return arrivalGateSpinner;
    }

    public JCheckBox getIsMealOnBoardCheckBox() {
        return isMealOnBoardCheckBox;
    }


    // !!!!!!!!!!!!!!!!!!!!!       Faire exception
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
    public Integer getPlaneId() {
        String planeText = (String) planeComboBox.getSelectedItem();
        return Integer.parseInt(getId(planeText));
    }
    public String getPilotId() {
        String pilotText = (String)pilotComboBox.getSelectedItem();
        return getId(pilotText);
    }
    public String getDepartureAirportId() {
        String departureAirportText = (String)departureAirportComboBox.getSelectedItem();
        return getId(departureAirportText);
    }
    public String getArrivalAirportId() {
        String arrivalAirportText = (String)arrivalAirportComboBox.getSelectedItem();
        return getId(arrivalAirportText);
    }

    public Flight getFlight() throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        GregorianCalendar departureDate = new GregorianCalendar(Integer.parseInt(departureYear.getValue().toString()), Integer.parseInt(departureMonth.getValue().toString()), Integer.parseInt(departureDay.getValue().toString()),
                Integer.parseInt(departureHour.getValue().toString()), Integer.parseInt(departureMinute.getValue().toString()));
        GregorianCalendar arrivalDate = new GregorianCalendar(Integer.parseInt(arrivalYear.getValue().toString()), Integer.parseInt(arrivalMonth.getValue().toString()), Integer.parseInt(arrivalDay.getValue().toString()),
                Integer.parseInt(arrivalHour.getValue().toString()), Integer.parseInt(arrivalMinute.getValue().toString()));

        return new Flight(
            numberTextField.getText(),
            departureDate,
            arrivalDate,
            isMealOnBoardCheckBox.isSelected(),
            mealDescriptionLabel.getText(),
            getPilotId(),
            Objects.requireNonNull(departureGateSpinner.getSelectedItem()).toString(),
            Objects.requireNonNull(arrivalGateSpinner.getSelectedItem()).toString(),
            getPlaneId())
        ;
    }

    private class MealOnBoardListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent evt){
            if(evt.getStateChange() == ItemEvent.SELECTED){
                mealDescriptionTextField.setEnabled(true);
            }else{
                mealDescriptionTextField.setEnabled(false);
            }
        }
    }
}
