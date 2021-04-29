package view.forms.flightForms;

import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

// Est ce que c'est possible de faire apparaitre ou disparaitre la description du repas en fonction de si on coche s'il y a un repas ou non ?

public class AddFlightForm extends JPanel {
    private JTextField numberTextField, textMealDescription, departureTerminalTextField, arrivalTerminalTextField;
    private JSpinner spinner;
    private JCheckBox mealOnBoard;
    private JLabel numberLabel, departureDateLabel, arrivalDateLabel, departureAirportLabel, departureTerminalLabel, departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescription, planeLabel, pilotLabel;
    private SpinnerNumberModel departureGateSpinner, arrivalGateSpinner;
    private SpinnerDateModel spinnerDateModel;
    private Date date;
    private JComboBox planeComboBox, pilotComboBox, departureAirportName, arrivalAirportComboBox;

    public AddFlightForm(){
        //this.setLayout(new GridLayout(15, 1, 2, 2));

        initForm();
    }

    public void initForm(){
        // number
        numberLabel = new JLabel("Numéro de vol : ");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(numberLabel);

        numberTextField = new JTextField();
        numberTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        numberTextField.setBounds(128,28,86,20);
        numberTextField.setColumns(1);
        this.add(numberTextField);

        // departureDate
        departureDateLabel = new JLabel("Date et heure de départ : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        date = new Date();
        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor departureDaySpinner = new JSpinner.DateEditor(spinner, "dd/MM/YYYY");
        spinner.setEditor(departureDaySpinner);
        this.add(departureDaySpinner);

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor departureTimeSpinner = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(departureTimeSpinner);
        this.add(departureTimeSpinner);

        // arrivalDate
        arrivalDateLabel = new JLabel("Date et heure d'arrivée : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);

        date = new Date();
        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor arrivalDaySpinner = new JSpinner.DateEditor(spinner, "dd/MM/YYYY");
        spinner.setEditor(arrivalDaySpinner);
        this.add(arrivalDaySpinner);

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor arrivalTimeSpinner = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(arrivalTimeSpinner);
        this.add(arrivalTimeSpinner);

        // departureAirport
        departureAirportLabel = new JLabel("Aéroport de départ : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);

        departureAirportName = new JComboBox();
        this.add(departureAirportName);

        // departureGate


        // arrivalAirport
        arrivalAirportLabel = new JLabel("Aéroport d'arrivée : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);

        arrivalAirportComboBox = new JComboBox();
        this.add(arrivalAirportComboBox);

        // arrivalGate
        arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalTextField = new JTextField();
        arrivalTerminalTextField.setEnabled(false);
        arrivalTerminalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalTextField);

        // plane
        planeLabel = new JLabel("Avion : ");
        planeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(planeLabel);

        planeComboBox = new JComboBox();
        this.add(planeComboBox);

        // pilot
        pilotLabel = new JLabel("Pilote : ");
        pilotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(pilotLabel);

        pilotComboBox = new JComboBox();
        this.add(pilotComboBox);

        // isMealOnBoard
        mealOnBoard = new JCheckBox("\nRepas à bord ");
        mealOnBoard.addItemListener(new MealOnBoardListener());
        mealOnBoard.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealOnBoard);

        // mealDescription
        mealDescription = new JLabel("Description du repas : ");
        mealDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescription);

        textMealDescription = new JTextField();
        textMealDescription.setEnabled(false);
        textMealDescription.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textMealDescription);
    }

    private class MealOnBoardListener implements ItemListener {
        public void actionPerformed(ActionEvent arg0) {
            if(numberTextField.getText().isEmpty()||(numberTextField.getText().isEmpty()))
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

    /*private class NumberListener implements FocusListener{

    }*/
}