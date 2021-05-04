package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class ModifyFlightForm extends JPanel {
    private JTextField mealDescriptionTextField, departureTerminalTextField, arrivalTerminalTextField;
    private JSpinner spinner, departureGateSpinner, arrivalGateSpinner;
    private JCheckBox isMealOnBoardCheckBox;
    private JLabel flightLabel, departureDateLabel, arrivalDateLabel, departureAirportLabel, departureTerminalLabel,
            departureGateLabel, arrivalAirportLabel, arrivalTerminalLabel, arrivalGateLabel, mealDescriptionLabel, planeLabel, pilotLabel, emptyLabel;
    private SpinnerNumberModel spinnerNumberModel;
    private SpinnerDateModel spinnerDateModel;
    private Date date;
    private JComboBox flightComboBox, planeComboBox, pilotComboBox, departureAirportComboBox, arrivalAirportComboBox;


    public ModifyFlightForm(){
        this.setLayout(new GridLayout(15, 1, 3, 3));

        flightLabel = new JLabel("Vol à modifier : ");
        flightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(flightLabel);

        flightComboBox = new JComboBox();
        this.add(flightComboBox);

        emptyLabel = new JLabel();
        emptyLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(emptyLabel);
        emptyLabel = new JLabel();
        emptyLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(emptyLabel);

        // faire apparaitre dans les cases du formulaire les données de la ligne de la tbale vol choisie
        // departureDate
        departureDateLabel = new JLabel("Date et heure de départ : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        date = new Date();
        spinnerDateModel = new SpinnerDateModel();
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor departureDaySpinner = new JSpinner.DateEditor(spinner);
        spinner.setEditor(departureDaySpinner);
        this.add(departureDaySpinner);
// NEW LINE
        // arrivalDate
        arrivalDateLabel = new JLabel("Date et heure d'arrivée : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);

        date = new Date();

        spinnerDateModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor arrivalDaySpinner = new JSpinner.DateEditor(spinner);
        spinner.setEditor(arrivalDaySpinner);
        this.add(arrivalDaySpinner);
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
        departureTerminalTextField.setHorizontalAlignment(SwingConstants.LEFT);
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
        //isMealOnBoardCheckBox.addItemListener(new AddFlightForm.MealOnBoardListener());
        isMealOnBoardCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isMealOnBoardCheckBox);
// NEW LINE

        emptyLabel = new JLabel();
        emptyLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(emptyLabel);

        // mealDescription
        mealDescriptionLabel = new JLabel("Description du repas : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextField = new JTextField();
        mealDescriptionTextField.setEnabled(false);
        mealDescriptionTextField.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealDescriptionTextField);
    }
}
