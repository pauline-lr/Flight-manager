package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FlightForm extends JPanel {
    private JTextField mealDescriptionTextField, departureTerminalTextField, arrivalTerminalTextField;
    private JSpinner departureGateSpinner, arrivalGateSpinner;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel  departureDateLabel, departureHourLabel, arrivalDateLabel, arrivalHourLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel, empty;
    private SpinnerNumberModel spinnerNumberModel;
    private JComboBox departureDay, departureMonth, departureYear, departureHour, departureMinute;
    private JComboBox arrivalDay, arrivalMonth, arrivalYear, arrivalHour, arrivalMinute;
    private JComboBox planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;

    public FlightForm(){
        setLayout(new GridLayout(10, 4));

        initForm();
    }

    public void initForm(){
        // departureDate
        departureDateLabel = new JLabel("Date de départ  : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        departureDay = new JComboBox();
        this.add(departureDay);
        //----
        departureDay.addFocusListener(new DepartureDayListener());
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

        departureAirportComboBox = new JComboBox();
        this.add(departureAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();
        // departureGate
        departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalTextField = new JTextField();
       // departureTerminalTextField.setEnabled(false);
        departureTerminalTextField.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(departureTerminalTextField);

        departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        spinnerNumberModel = new SpinnerNumberModel(0,0,999,1);
        departureGateSpinner = new JSpinner(spinnerNumberModel);
        this.add(departureGateSpinner);



// NEW LINE
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
// NEW LINE
        addEmptyLabel();
// NEW LINE
        // arrivalAirport
        arrivalAirportLabel = new JLabel("Aéroport d'arrivée : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);

        arrivalAirportComboBox = new JComboBox();
        this.add(arrivalAirportComboBox);

        addEmptyLabel();
        addEmptyLabel();

        // arrivalGate
        arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalTextField = new JTextField();
        //arrivalTerminalTextField.setEnabled(false);
        arrivalTerminalTextField.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(arrivalTerminalTextField);

        arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);

        spinnerNumberModel = new SpinnerNumberModel(0,0,999,1);
        arrivalGateSpinner = new JSpinner(spinnerNumberModel);
        this.add(arrivalGateSpinner);
// NEW LINE
        // plane
        planeLabel = new JLabel("Avion : ");
        planeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(planeLabel);

        planeComboBox = new JComboBox();
        this.add(planeComboBox);
// NEW LINE
        // pilot
        pilotLabel = new JLabel("Pilote : ");
        pilotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(pilotLabel);

        pilotComboBox = new JComboBox();
        this.add(pilotComboBox);

        // isMealOnBoard
        isMealOnBoardCheckBox = new JCheckBox("Repas à bord");
        isMealOnBoardCheckBox.addItemListener(new MealOnBoardListener());
        isMealOnBoardCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isMealOnBoardCheckBox);
// NEW LINe
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

    public JTextField getDepartureTerminalTextField() {
        return departureTerminalTextField;
    }

    public JTextField getArrivalTerminalTextField() {
        return arrivalTerminalTextField;
    }

    public JSpinner getDepartureGateSpinner() {
        return departureGateSpinner;
    }

    public JSpinner getArrivalGateSpinner() {
        return arrivalGateSpinner;
    }

    public JCheckBox getIsMealOnBoardCheckBox() {
        return isMealOnBoardCheckBox;
    }

    public SpinnerNumberModel getSpinnerNumberModel() {
        return spinnerNumberModel;
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

    public JComboBox getPlaneComboBox() {
        return planeComboBox;
    }

    public JComboBox getPilotComboBox() {
        return pilotComboBox;
    }

    public JComboBox getDepartureAirportComboBox() {
        return departureAirportComboBox;
    }

    public JComboBox getArrivalAirportComboBox() {
        return arrivalAirportComboBox;
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

    private class DepartureDayListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
        }
    }

    private class DepartureTerminalListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            
        }

        @Override
        public void focusLost(FocusEvent e) {
            try{

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }


}
