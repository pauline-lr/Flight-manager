package view.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

public class AddFlightForm extends JPanel {
    private JTextField textNumber, textMealDescription;
    private JSpinner flightTimeHour, stopTime, spinner;
    private JCheckBox mealOnBoard, halt;
    private JLabel number,flightTime, departure, mealDescription, stop, plane, airport, pilot;
    private SpinnerNumberModel flightTimeModel, stopTimeModel;
    private SpinnerDateModel spinnerModel;
    private Date date;
    private JComboBox typePlane, pilotName;

    public AddFlightForm(){
        this.setLayout(new GridLayout(8, 2, 5, 5));

        number = new JLabel("Numéro : ");
        number.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(number);
        textNumber = new JTextField();
        this.add(textNumber);

        flightTime = new JLabel("Durée du vol (en minutes) : ");
        flightTime.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(flightTime);

        flightTimeModel = new SpinnerNumberModel(0,0,300,1);
        flightTimeHour = new JSpinner(flightTimeModel);
        add(flightTimeHour);

        departure = new JLabel("Départ : ");
        departure.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departure);

        date = new Date();
        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new javax.swing.JSpinner(spinnerModel);
        JSpinner.DateEditor departureDate = new JSpinner.DateEditor(spinner, "dd-MM-YYYY");
        spinner.setEditor(departureDate);
        add(departureDate);

        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new javax.swing.JSpinner(spinnerModel);
        JSpinner.DateEditor departureTime = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(departureTime);
        add(departureTime);

        mealOnBoard = new JCheckBox("\nRepas à bord ");
        mealOnBoard.addItemListener(new MealOnBoardListener());;
        add(mealOnBoard);

        mealDescription = new JLabel("Description du repas : ");
        mealDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescription);

        textMealDescription = new JTextField();
        textMealDescription.setEnabled(false);
        add(textMealDescription);

        halt = new JCheckBox("\nEscale ");
        halt.addItemListener(new HaltListener());;
        add(halt);

        stop = new JLabel("Durée de l'escale : ");
        stop.setHorizontalAlignment(SwingConstants.RIGHT);
        add(stop);

        stopTimeModel = new SpinnerNumberModel(0,0,300,1);
        stopTime = new JSpinner(stopTimeModel);
        add(stopTime);

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
}