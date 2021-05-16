package view.forms.flightForms;

import controller.*;
import exception.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class FlightForm extends JPanel {
    private static final int LENGTH_PILOT_ID = 7;
    private static final int LENGTH_AIRPORT_ID = 3;

    private ApplicationController controller = new ApplicationController();

    private JTextField numberTextField, mealDescriptionTextField;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel  numberLabel, departureDateLabel, departureHourLabel, arrivalDateLabel, arrivalHourLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel, empty;
    private JComboBox departureDay, departureMonth, departureYear, departureHour, departureMinute;
    private JComboBox arrivalDay, arrivalMonth, arrivalYear, arrivalHour, arrivalMinute;
    private JComboBox departureGateSpinner;
    private JComboBox arrivalGateSpinner;
    private JComboBox planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;
    private JComboBox departureTerminalComboBox, arrivalTerminalComboBox;

    public FlightForm(){
        setLayout(new GridLayout(10, 4));

        initForm();
    }

    public void initForm(){
        numberLabel = new JLabel("N° de vol : ");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(numberLabel);

        numberTextField = new JTextField();
        numberTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(numberTextField);

        addEmptyLabel();
        addEmptyLabel();

        // departureDate
        departureDateLabel = new JLabel("Date de départ  : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        departureDay = new JComboBox();
        this.add(departureDay);
        departureMonth = new JComboBox();
        this.add(departureMonth);
        departureYear = new JComboBox();
        this.add(departureYear);

        departureHourLabel = new JLabel("Heure de départ  : ");
        departureHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureHourLabel);

        departureHour = new JComboBox();
        this.add(departureHour);
        departureMinute = new JComboBox();
        this.add(departureMinute);

        addEmptyLabel();

        // departureAirport
        departureAirportLabel = new JLabel("Aéroport de départ : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);
        try {
            departureAirportComboBox = new JComboBox(controller.getAllAirportsCode().toArray());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(departureAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();

        // departureGate
        departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalComboBox = new JComboBox();
        this.add(departureTerminalComboBox);

        departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        departureGateSpinner = new JComboBox();
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

        arrivalDay = new JComboBox();
        this.add(arrivalDay);
        arrivalMonth = new JComboBox();
        this.add(arrivalMonth);
        arrivalYear = new JComboBox();
        this.add(arrivalYear);

        arrivalHourLabel = new JLabel("Heure de départ  : ");
        arrivalHourLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalHourLabel);

        arrivalHour = new JComboBox();
        this.add(arrivalHour);
        arrivalMinute = new JComboBox();
        this.add(arrivalMinute);

        addEmptyLabel();

        // arrivalAirport
        arrivalAirportLabel = new JLabel("Aéroport d'arrivée : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);
        try {
            arrivalAirportComboBox = new JComboBox(controller.getAllAirportsCode().toArray());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(arrivalAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();

        // arrivalGate
        arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalComboBox = new JComboBox();
        this.add(arrivalTerminalComboBox);

        arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);


        arrivalGateSpinner = new JComboBox();
        this.add(arrivalGateSpinner);

        // plane
        planeLabel = new JLabel("Avion : ");
        planeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(planeLabel);
        try {
            planeComboBox = new JComboBox(controller.getAllPlanesID().toArray());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(planeComboBox);

        // pilot
        pilotLabel = new JLabel("Pilote : ");
        pilotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(pilotLabel);
        try {
            pilotComboBox = new JComboBox(controller.getAllPilotsLicenceNumber().toArray());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(pilotComboBox);

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

    public JComboBox getDepartureDay() {
        return departureDay;
    }

    public JComboBox getDepartureMonth() {
        return departureMonth;
    }

    public JComboBox getDepartureYear() {
        return departureYear;
    }

    public JComboBox getDepartureHour() {
        return departureHour;
    }

    public JComboBox getDepartureMinute() {
        return departureMinute;
    }

    public JComboBox getArrivalDay() {
        return arrivalDay;
    }

    public JComboBox getArrivalMonth() {
        return arrivalMonth;
    }

    public JComboBox getArrivalYear() {
        return arrivalYear;
    }

    public JComboBox getArrivalHour() {
        return arrivalHour;
    }

    public JComboBox getArrivalMinute() {
        return arrivalMinute;
    }

    public Integer getPlaneId() {
        String planeText = (String) planeComboBox.getSelectedItem();
        String [] res = planeText != null ? planeText.split(" ") : new String[0];
        return Integer.parseInt(res[0]);
    }

    public String getPilotId() {
        String pilotText = (String) pilotComboBox.getSelectedItem();
        String [] res = pilotText.split("");
        for (int i = 0; i < LENGTH_PILOT_ID; i++) {
            pilotId.append(res[i]);
        }

        return pilotId.toString();
    }

    public String getDepartureAirportComboBox() {
        return obtainAirportId(departureAirportComboBox);
    }

    public String getArrivalAirportComboBox() {
        return obtainAirportId(arrivalAirportComboBox);
    }

    public String obtainAirportId(JComboBox airport){
        String airport_Id = " ";
        String airportText = (String) airport.getSelectedItem();
        String [] res = airportText.split(" ");
        for (int i = 0; i < LENGTH_AIRPORT_ID; i++) {
            airport_Id += res[i];
        }

        return airport_Id;
    }

    public Flight getFlight() throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        GregorianCalendar departureDate = new GregorianCalendar(departureYear.getSelectedIndex(), departureMonth.getSelectedIndex(), departureDay.getSelectedIndex(), departureHour.getSelectedIndex(), departureMinute.getSelectedIndex());
        GregorianCalendar arrivalDate = new GregorianCalendar(arrivalYear.getSelectedIndex(), arrivalMonth.getSelectedIndex(), arrivalDay.getSelectedIndex(), arrivalHour.getSelectedIndex(), arrivalMinute.getSelectedIndex());

        Flight flight = new Flight(numberTextField.getText(), departureDate, arrivalDate, isMealOnBoardCheckBox.isSelected(),
                mealDescriptionLabel.getText(), getPilotId(), Objects.requireNonNull(departureGateSpinner.getSelectedItem()).toString(),
                Objects.requireNonNull(arrivalGateSpinner.getSelectedItem()).toString(), getPlaneId());
        return flight;
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
