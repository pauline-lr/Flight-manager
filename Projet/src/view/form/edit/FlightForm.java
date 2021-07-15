package view.form.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.NotMatchException;
import exception.TextLengthException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import exception.dataBase.ModifyException;
import model.Flight;
import tool.Format;
import tool.GetID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightForm extends JPanel {
    private final static String REGEX_NUMBER = "^[A-Z][A-Z]\\d{4}$";

    private ApplicationController controller;
    private Date currentDate;
    private JTextField flightNumberTextField;
    private JTextArea mealDescriptionTextArea;
    private JCheckBox isMealOnBoardCheckBox;
    private JSpinner departureDate, departureTime, arrivalDate, arrivalTime;
    private JComboBox<String> pilotComboBox, planeComboBox,
            departureAirportComboBox, departureTerminalComboBox, departureGateComboBox,
            arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox;
    private GridLayout grid;

    public FlightForm() throws AllDataException, DataBaseConnectionException {
        setController(new ApplicationController());
        setCurrentDate(new Date());
        grid = new GridLayout(15, 5);
        createFlightForm();
        setLayout(grid);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    private void createFlightForm() throws AllDataException, DataBaseConnectionException {
        addFlightNumberField();
        addPilotField();
        addPlaneField();
        addDepartureMomentField();
        addDepartureLocationField();
        addArrivalMomentField();
        addArrivalLocationField();
        addMealOnBoardField();
    }

    public Flight getFlight() throws NotMatchException, TextLengthException, FlightException.NumberFlightException, FlightException.DepartureDateException {
        return new Flight(
            getFlightNumberTextField().getText(),
            getDeparture(),
            getArrival(),
            isMealOnBoardCheckBox.isSelected(),
            getMealDescriptionTextArea(),
            GetID.getPilotID(pilotComboBox),
            GetID.getGateID(departureAirportComboBox, departureTerminalComboBox, departureGateComboBox),
            GetID.getGateID(arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox),
            GetID.getPlaneID(planeComboBox)
        );
    }

    private GregorianCalendar setFullDate(GregorianCalendar date, GregorianCalendar time) {
        return new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE));
    }

    //region Fields
    private void addFlightNumberField() {
        JLabel flightNumberLabel = new JLabel("    Numéro");
        flightNumberLabel.setFont(Format.titleFont);
        flightNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightNumberLabel);

        flightNumberTextField = new JTextField();
        flightNumberTextField.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightNumberTextField);

        addEmptyField();
        addEmptyField();
        addEmptyField();
    }

    private void addPilotField() {
        JLabel pilotLabel = new JLabel("    Pilote");
        pilotLabel.setFont(Format.titleFont);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(pilotLabel);

        try {
            pilotComboBox = new JComboBox<>(controller.getAllAvailablePilotsToString());
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
        planeLabel.setFont(Format.titleFont);
        planeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(planeLabel);

        try {
            planeComboBox = new JComboBox<>(controller.getAllAvailablePlanesToString());
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
        departureLabel.setFont(Format.titleFont);
        departureLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(departureLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // departureDate
        JLabel departureDateLabel = new JLabel("Date : ");
        departureDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureDateLabel);

        departureDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DATE));
        departureDate.setEditor(new JSpinner.DateEditor(departureDate, "dd/MM/yyyy"));
        this.add(departureDate);

        // departureTime
        JLabel departureTimeLabel = new JLabel("Heure : ");
        departureTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTimeLabel);

        departureTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.HOUR_OF_DAY));
        departureTime.setEditor(new JSpinner.DateEditor(departureTime, "HH:mm"));
        this.add(departureTime);

        addEmptyField();
    }

    private void addDepartureLocationField() throws DataBaseConnectionException, AllDataException {
        // departureAirport
        JLabel departureAirportLabel = new JLabel("Aéroport : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);

        try {
            departureAirportComboBox = new JComboBox<>(controller.getAllAirportsToString());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        departureAirportComboBox.addItemListener(new departureAirportComboBoxListener());
        this.add(departureAirportComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();

        // departureTerminal
        JLabel departureTerminalLabel = new JLabel("Terminal : ");
        departureTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTerminalLabel);

        departureTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportToString(GetID.getAirportID(departureAirportComboBox)));

        departureTerminalComboBox.addItemListener(new departureTerminalComboBoxListener());
        this.add(departureTerminalComboBox);

        // departureGate
        JLabel departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);


        departureGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem()));
        this.add(departureGateComboBox);
    }

    private void addArrivalMomentField() {
        // arrival
        JLabel arrivalLabel = new JLabel("    Arrivée");
        arrivalLabel.setFont(Format.titleFont);
        arrivalLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(arrivalLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        // arrivalDate
        JLabel arrivalDateLabel = new JLabel("Date : ");
        arrivalDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalDateLabel);


        // arrivalDate
        arrivalDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DATE));
        arrivalDate.setEditor(new JSpinner.DateEditor(arrivalDate, "dd/MM/yyyy"));
        this.add(arrivalDate);

        // arrivalTime
        JLabel arrivalTimeLabel = new JLabel("Heure : ");
        arrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTimeLabel);

        arrivalTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.HOUR_OF_DAY));
        arrivalTime.setEditor(new JSpinner.DateEditor(arrivalTime, "HH:mm"));
        this.add(arrivalTime);

        addEmptyField();
    }

    private void addArrivalLocationField() throws DataBaseConnectionException, AllDataException {
        // arrivalAirport
        JLabel arrivalAirportLabel = new JLabel("Aéroport : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);

        try {
            arrivalAirportComboBox = new JComboBox<>(controller.getAllAirportsToString());
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        arrivalAirportComboBox.addItemListener(new arrivalAirportComboBoxListener());
        this.add(arrivalAirportComboBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();

        // arrivalTerminal
        JLabel arrivalTerminalLabel = new JLabel("Terminal : ");
        arrivalTerminalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTerminalLabel);

        arrivalTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportToString(GetID.getAirportID(arrivalAirportComboBox)));

        arrivalTerminalComboBox.addItemListener(new arrivalTerminalComboBoxListener());
        this.add(arrivalTerminalComboBox);

        // arrivalGate
        JLabel arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);

        arrivalGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem()));

        this.add(arrivalGateComboBox);
    }

    private void addMealOnBoardField()  {
        JLabel mealLabel = new JLabel("    Repas");
        mealLabel.setFont(Format.titleFont);
        mealLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(mealLabel);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        isMealOnBoardCheckBox = new JCheckBox("Repas");
        isMealOnBoardCheckBox.addItemListener(new MealOnBoardListener());
        isMealOnBoardCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(isMealOnBoardCheckBox);

        addEmptyField();
        addEmptyField();
        addEmptyField();
        addEmptyField();

        JLabel mealDescriptionLabel = new JLabel("Description : ");
        mealDescriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        add(mealDescriptionLabel);

        mealDescriptionTextArea = new JTextArea();
        mealDescriptionTextArea.setEnabled(false);
        add(mealDescriptionTextArea);

        addEmptyField();
        addEmptyField();
    }

    private void addEmptyField() {
        JLabel empty = new JLabel();
        empty.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        add(empty);
    }
    //endregion

    //region Set
    public void setFlightNumberComboBox(String flightNumber) {
        flightNumberTextField.setText(flightNumber);
    }

    public void setPilotComboBox(String pilotID) throws DataBaseConnectionException, AllDataException  {
        pilotComboBox.setSelectedItem(controller.getPilotToString(pilotID));
    }

    public void setPlaneComboBox(Integer planeID) throws DataBaseConnectionException, AllDataException   {
        planeComboBox.setSelectedItem(controller.getPlaneToString(planeID));
    }

    public void setDepartureDate(GregorianCalendar date) {
        departureDate.setValue(date.getTime());
    }

    public void setDepartureTime(GregorianCalendar time) {
        departureTime.setValue(time.getTime());
    }

    public void setArrivalDate(GregorianCalendar date) {
        arrivalDate.setValue(date.getTime());
    }

    public void setArrivalTime(GregorianCalendar time) {
        arrivalTime.setValue(time.getTime());
    }

    public void setDepartureAirportComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        departureAirportComboBox.setSelectedItem(controller.getAirportToString(gateID));
    }

    public void setDepartureTerminalComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        departureTerminalComboBox.setSelectedItem(controller.getTerminalToString(gateID));
    }

    public void setDepartureGateComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        departureGateComboBox.setSelectedItem(controller.getGateToString(gateID));
    }

    public void setArrivalAirportComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        arrivalAirportComboBox.setSelectedItem(controller.getAirportToString(gateID));
    }

    public void setArrivalTerminalComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        arrivalTerminalComboBox.setSelectedItem(controller.getTerminalToString(gateID));
    }

    public void setArrivalGateComboBox(String gateID) throws DataBaseConnectionException, AllDataException {
        arrivalGateComboBox.setSelectedItem(controller.getGateToString(gateID));
    }

    public void setIsMealOnBoardCheckBox(Boolean isMealOnBoard) {
        isMealOnBoardCheckBox.setSelected(isMealOnBoard);
    }

    public void setMealDescriptionTextArea(String textArea)  {
        mealDescriptionTextArea.setText(textArea);
    }

    //endregion

    //region Listeners
    private class departureAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            String[] updatedTerminalsOfAnAirportForComboBox = new String[0];
            try {
                updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportToString(GetID.getAirportID(departureAirportComboBox));
            } catch (AllDataException | DataBaseConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
            String[] updatedGatesOfAnAirportAndTerminalForComboBox = new String[0];
            try {
                updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }

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
        }
    }

    private class departureTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            String[] updatedGatesOfAnAirportAndTerminalForComboBox = new String[0];
            try {
                updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
            departureGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                }
        }
    }

    private class arrivalAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            String[] updatedTerminalsOfAnAirportForComboBox = new String[0];
            try {
                updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportToString(GetID.getAirportID(arrivalAirportComboBox));
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
            String[] updatedGatesOfAnAirportAndTerminalForComboBox = new String[0];
            try {
                updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
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
        }
    }

    private class arrivalTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            String[] updatedGatesOfAnAirportAndTerminalForComboBox = new String[0];
            try {
                updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalToString(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            }
            arrivalGateComboBox.removeAllItems();
            if (event.getStateChange() == ItemEvent.SELECTED) {
                for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                    arrivalGateComboBox.addItem(gate);
                }
            }
        }
    }

    private class MealOnBoardListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            mealDescriptionTextArea.setEnabled(itemEvent.getStateChange() == ItemEvent.SELECTED);
        }
    }
    //endregion

    public Boolean checkFlightNumberIsExisting() throws DataBaseConnectionException, ModifyException {
        String flightNumber = flightNumberTextField.getText();
        Boolean isFlightNumberExisting = controller.flightNumberIsExisting(flightNumber);
        if (isFlightNumberExisting) {
            JOptionPane.showMessageDialog(null, "Le numéro de vol \"" + flightNumber + "\" existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return isFlightNumberExisting;
    }

    public JTextField getFlightNumberTextField()  {
        String number = flightNumberTextField.getText();
        Pattern pattern = Pattern.compile(REGEX_NUMBER);
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return flightNumberTextField;
        } else {
            JOptionPane.showMessageDialog(null, "Le numéro de vol : " + number + " ne correspond pas à la structure requise : \n" +
                    "2 lettres majuscules et 4 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public String getMealDescriptionTextArea() throws TextLengthException {
        String mealDescriptionText = mealDescriptionTextArea.getText();
        if(mealDescriptionText.length() > 400)
            throw new TextLengthException("La description du repas est trop longue. Maximum 400 caractères.");
        if (mealDescriptionText.equals(""))
            mealDescriptionText = null;
        return mealDescriptionText;
    }

    public GregorianCalendar getDeparture() throws FlightException.DepartureDateException {
        GregorianCalendar departureDateGC = setFullDate(Format.getDate(departureDate), Format.getDate(departureTime));
        if(departureDateGC.compareTo(new GregorianCalendar()) < 0)
            throw new FlightException.DepartureDateException();
        return departureDateGC;
    }

    public GregorianCalendar getArrival() throws FlightException.DepartureDateException {
        GregorianCalendar arrivalDateGC = setFullDate(Format.getDate(arrivalDate), Format.getDate(arrivalTime));
        if(arrivalDateGC.compareTo(getDeparture()) > 0)
            throw new FlightException.DepartureDateException();
        return arrivalDateGC;
    }
}
