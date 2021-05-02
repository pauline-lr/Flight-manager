package view.forms.flightForms;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

// Est ce que c'est possible de faire apparaitre ou disparaitre la description du repas en fonction de si on coche s'il y a un repas ou non ?
// Est ce que c'est possible de mettre des retours a la ligne la ou j'ai mis des commentaires pour voir ce que ca donne ?

public class AddFlightForm extends JPanel {
    private JTextField numberTextField, mealDescriptionTextField, departureTerminalTextField, arrivalTerminalTextField;
    private JSpinner spinner, departureGateSpinner, arrivalGateSpinner;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel numberLabel, departureDateLabel, arrivalDateLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel;
    private SpinnerNumberModel spinnerNumberModel;
    private SpinnerDateModel spinnerDateModel;
    private Date date;
    private JComboBox planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;

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
// NEW LINE
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
// NEW LINE
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
// NEW LINE
        // departureAirport
        departureAirportLabel = new JLabel("Aéroport de départ : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);

        departureAirportComboBox = new JComboBox();
        this.add(departureAirportComboBox);

        // departureGate
        departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalTextField = new JTextField();
        departureTerminalTextField.setEnabled(false);
        departureTerminalTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalTextField);

        departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        spinnerNumberModel = new SpinnerNumberModel(0,0,999,1);
        departureGateSpinner = new JSpinner(spinnerNumberModel);
        this.add(departureGateSpinner);
// NEW LINE
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
// NEW LINE
        // mealDescription
        mealDescriptionLabel = new JLabel("Description du repas : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextField = new JTextField();
        mealDescriptionTextField.setEnabled(false);
        mealDescriptionTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionTextField);
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
                mealDescriptionTextField.setEnabled(true);
            }else{
                mealDescriptionTextField.setEnabled(false);
            }
        }
    }

    /*private class NumberListener implements FocusListener{

    }*/
}