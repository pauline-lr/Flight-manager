package view.form.edit;

import controller.*;
import exception.*;
import exception.dataBase.DataBaseConnectionException;
import model.*;
import tool.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

public class FlightForm extends JPanel {
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

    public FlightForm() {
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

    private void createFlightForm() {
        addFlightNumberField();
        addPilotField();
        addPlaneField();
        addDepartureMomentField();
        addDepartureLocationField();
        addArrivalMomentField();
        addArrivalLocationField();
        addMealOnBoardField();
    }

    public Flight getFlight() {
        try {
            return new Flight(
                    flightNumberTextField.getText(),
                    Format.getDate(departureDate),
                    Format.getDate(arrivalDate),
                    isMealOnBoardCheckBox.isSelected(),
                    mealDescriptionTextArea.getText(),
                    GetID.getPilotID(pilotComboBox),
                    GetID.getGateID(departureAirportComboBox, departureTerminalComboBox, departureGateComboBox),
                    GetID.getGateID(arrivalAirportComboBox, arrivalTerminalComboBox, arrivalGateComboBox),
                    GetID.getPlaneID(planeComboBox)
            );
        } catch (FlightException.NumberFlightException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (FlightException.MealDescriptionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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
            pilotComboBox = new JComboBox<>(controller.getAllPilotsForComboBox());
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
            planeComboBox = new JComboBox<>(controller.getAllPlanesForComboBox());
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

        departureDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureDate.setEditor(new JSpinner.DateEditor(departureDate, "dd/MM/yyyy"));
        this.add(departureDate);

        // departureTime
        JLabel departureTimeLabel = new JLabel("Heure : ");
        departureTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureTimeLabel);

        departureTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        departureTime.setEditor(new JSpinner.DateEditor(departureTime, "HH:mm"));
        this.add(departureTime);

        addEmptyField();
    }

    private void addDepartureLocationField() {
        // departureAirport
        JLabel departureAirportLabel = new JLabel("Aéroport : ");
        departureAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureAirportLabel);

        try {
            departureAirportComboBox = new JComboBox<>(controller.getAllAirportsForComboBox());
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

        try {
            departureTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportID(departureAirportComboBox)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        departureTerminalComboBox.addItemListener(new departureTerminalComboBoxListener());
        this.add(departureTerminalComboBox);

        // departureGate
        JLabel departureGateLabel = new JLabel("Porte : ");
        departureGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(departureGateLabel);

        try {
            departureGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
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
        arrivalDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        arrivalDate.setEditor(new JSpinner.DateEditor(arrivalDate, "dd/MM/yyyy"));
        this.add(arrivalDate);

        // arrivalTime
        JLabel arrivalTimeLabel = new JLabel("Heure : ");
        arrivalTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalTimeLabel);

        arrivalTime = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        arrivalTime.setEditor(new JSpinner.DateEditor(arrivalTime, "HH:mm"));
        this.add(arrivalTime);

        addEmptyField();
    }

    private void addArrivalLocationField() {
        // arrivalAirport
        JLabel arrivalAirportLabel = new JLabel("Aéroport : ");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalAirportLabel);

        try {
            arrivalAirportComboBox = new JComboBox<>(controller.getAllAirportsForComboBox());
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

        try {
            arrivalTerminalComboBox = new JComboBox<>(controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportID(arrivalAirportComboBox)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        arrivalTerminalComboBox.addItemListener(new arrivalTerminalComboBoxListener());
        this.add(arrivalTerminalComboBox);

        // arrivalGate
        JLabel arrivalGateLabel = new JLabel("Porte : ");
        arrivalGateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(arrivalGateLabel);

        try {
            arrivalGateComboBox = new JComboBox<>(controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.add(arrivalGateComboBox);
    }

    private void addMealOnBoardField() {
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

    public void setPilotComboBox(String pilotID) throws SQLException, DataBaseConnectionException {
        pilotComboBox.setSelectedItem(controller.getPilotToString(pilotID));
    }

    public void setPlaneComboBox(Integer planeID) throws SQLException, DataBaseConnectionException {
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

    public void setDepartureAirportComboBox(String gateID) throws SQLException, DataBaseConnectionException {
        departureAirportComboBox.setSelectedItem(controller.getAirportToString(gateID));
    }

    public void setDepartureTerminalComboBox(String gateID) throws SQLException, DataBaseConnectionException {
        departureTerminalComboBox.setSelectedItem(controller.getTerminalToString(gateID));
    }

    public void setDepartureGateComboBox(String gateID) throws SQLException, DataBaseConnectionException {
        departureGateComboBox.setSelectedItem(controller.getGateToString(gateID));
    }

    public void setArrivalAirportComboBox(String gateID) throws SQLException, DataBaseConnectionException {
        arrivalAirportComboBox.setSelectedItem(controller.getAirportToString(gateID));
    }

    public void setArrivalTerminalComboBox(String gateID) throws SQLException, DataBaseConnectionException {
        arrivalTerminalComboBox.setSelectedItem(controller.getTerminalToString(gateID));
    }

    public void setArrivalGateComboBox(String gateID) throws SQLException, DataBaseConnectionException {
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
            try {
                String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportID(departureAirportComboBox));
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
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
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class departureTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(departureAirportComboBox), (String) departureTerminalComboBox.getSelectedItem());
                departureGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        departureGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class arrivalAirportComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedTerminalsOfAnAirportForComboBox = controller.getAllTerminalsOfAnAirportForComboBox(GetID.getAirportID(arrivalAirportComboBox));
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
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
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private class arrivalTerminalComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            try {
                String[] updatedGatesOfAnAirportAndTerminalForComboBox = controller.getAllGatesOfAnAirportAndTerminalForComboBox(GetID.getAirportID(arrivalAirportComboBox), (String) arrivalTerminalComboBox.getSelectedItem());
                arrivalGateComboBox.removeAllItems();
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    for (String gate : updatedGatesOfAnAirportAndTerminalForComboBox) {
                        arrivalGateComboBox.addItem(gate);
                    }
                }
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
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
}
