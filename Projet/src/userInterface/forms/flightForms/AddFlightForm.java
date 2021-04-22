package userInterface.forms.flightForms;

import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

public class AddFlightForm extends JPanel {
    private JTextField textNumber, textMealDescription;
    private JSpinner flightTimeHour, stopTime, spinner;
    private JCheckBox mealOnBoard, halt;
    private JLabel number,flightTime, departure, departureAirport, arrivalAirport, mealDescription, stop, plane, pilot;
    private SpinnerNumberModel flightTimeModel, stopTimeModel;
    private SpinnerDateModel spinnerModel;
    private Date date;
    private JComboBox typePlane, pilotName, departureAirportName, arrivalAirportName;

    public AddFlightForm(){
        //this.setLayout(new GridLayout(15, 1, 2, 2));

        initForm();
    }

    public void initForm(){
        number = new JLabel("Numéro : ");
        number.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(number);
        textNumber = new JTextField();
        textNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        textNumber.setBounds(128,28,86,20);
        textNumber.setColumns(1);
        this.add(textNumber);

        flightTime = new JLabel("Durée du vol (en minutes) : ");
        flightTime.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(flightTime);

        flightTimeModel = new SpinnerNumberModel(0,0,300,1);
        flightTimeHour = new JSpinner(flightTimeModel);
        flightTimeHour.setBounds(128,28,86,20);
        add(flightTimeHour);

        departure = new JLabel("Départ : ");
        departure.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departure);

        date = new Date();
        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor departureDate = new JSpinner.DateEditor(spinner, "dd-MM-YYYY");
        spinner.setEditor(departureDate);
        add(departureDate);

        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor departureTime = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(departureTime);
        add(departureTime);

        halt = new JCheckBox("\nEscale ");
        halt.addItemListener(new HaltListener());;
        halt.setHorizontalAlignment(SwingConstants.RIGHT);
        add(halt);

        stop = new JLabel("Durée de l'escale : ");
        stop.setHorizontalAlignment(SwingConstants.RIGHT);
        add(stop);

        stopTimeModel = new SpinnerNumberModel(0,0,300,1);
        stopTime = new JSpinner(stopTimeModel);
        add(stopTime);

        departureAirport = new JLabel("Aéroport de Départ : ");
        departureAirport.setHorizontalAlignment(SwingConstants.RIGHT);
        add(departureAirport);
        departureAirportName = new JComboBox();
        this.add(departureAirportName);

        arrivalAirport = new JLabel("Aéroport d'arrivée : ");
        arrivalAirport.setHorizontalAlignment(SwingConstants.RIGHT);
        add(arrivalAirport);
        arrivalAirportName = new JComboBox();
        this.add(arrivalAirportName);

        mealOnBoard = new JCheckBox("\nRepas à bord ");
        mealOnBoard.addItemListener(new MealOnBoardListener());
        mealOnBoard.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealOnBoard);

        mealDescription = new JLabel("Description du repas : ");
        mealDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescription);

        textMealDescription = new JTextField();
        textMealDescription.setEnabled(false);
        textMealDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textMealDescription);



        plane = new JLabel("Avion");
        plane.setHorizontalAlignment(SwingConstants.RIGHT);
        add(plane);
        typePlane = new JComboBox();
        this.add(typePlane);

        pilot = new JLabel("Pilote");
        pilot.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pilot);
        pilotName = new JComboBox();
        this.add(pilotName);
    }

    private class MealOnBoardListener implements ItemListener {
        public void actionPerformed(ActionEvent arg0) {
            if(textNumber.getText().isEmpty()||(textNumber.getText().isEmpty()))
                JOptionPane.showMessageDialog(null, "Data Missing");
            else
                JOptionPane.showMessageDialog(null, "Data Submitted");
        }
        @Override
        public void itemStateChanged(ItemEvent evt){
            if(evt.getStateChange() == ItemEvent.SELECTED){
                textMealDescription.setEnabled(true);
            }else{
                textMealDescription.setEnabled(false);
            }
        }
    }

    private class HaltListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent evt){
            if(evt.getStateChange() == ItemEvent.SELECTED){
                stopTime.setEnabled(true);
            }else{
                stopTime.setEnabled(false);
            }
        }
    }

    /*private class NumberListener implements FocusListener{

    }*/
}