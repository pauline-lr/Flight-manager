package view.tables;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListAllFlightsTable extends AbstractTableModel {
    private ApplicationController controller = new ApplicationController();
    private ArrayList<String> columnNames;
    private ArrayList<Flight> flights;

    public ListAllFlightsTable() throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        setColumnNames();
        setFlights(controller.getAllFlights());
    }

    private void setColumnNames() {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro");
        columnNames.add("Pilote");
        columnNames.add("Avion");
        columnNames.add("Heure de départ");
        columnNames.add("Date de départ");
        columnNames.add("Aéroport de départ");
        columnNames.add("Terminal de départ");
        columnNames.add("Porte de départ");
        columnNames.add("Heure d'arrivée");
        columnNames.add("Date d'arrivée");
        columnNames.add("Aéroport d'arrivée");
        columnNames.add("Terminal d'arrivée");
        columnNames.add("Porte d'arrivée");
        columnNames.add("Repas");
        columnNames.add("Description du repas");
    }

    private void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public int getColumnCount() {
        return columnNames.size();
    }
    public int getRowCount() {
        return flights.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        Flight flight = flights.get(row);
        GregorianCalendar departure = flight.getDepartureTime();
        GregorianCalendar arrival = flight.getArrivalTime();
        switch (column) {
            case 0:
                return flight.getNumber();
            case 1:
                try {
                    return controller.getPilotToString(flight.getPilot());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 2:
                try {
                    return controller.getPlaneToString(flight.getPlane());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 3:
                return timeFormat(departure);
            case 4:
                return dateFormat(departure);
            case 5:
                try {
                    return controller.getAirportToString(flight.getDepartureGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 6:
                try {
                    return controller.getTerminalToString(flight.getDepartureGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 7:
                try {
                    return controller.getGateToString(flight.getDepartureGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 8:
                return timeFormat(arrival);
            case 9:
                return dateFormat(arrival);
            case 10:
                try {
                    return controller.getAirportToString(flight.getArrivalGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 11:
                try {
                    return controller.getTerminalToString(flight.getArrivalGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 12:
                try {
                    return controller.getGateToString(flight.getArrivalGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 13:
                return flight.getMealOnBoard() ? "Oui" : "Non";
            case 14:
                return flight.getMealDescription() == null ? null : flight.getMealDescription();
            default:
                return null;
        }
    }

    public static String timeFormat(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setCalendar(calendar);

        return format.format(calendar.getTime());
    }
    public static String dateFormat(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setCalendar(calendar);

        return format.format(calendar.getTime());
    }

    public Class getColumnClass(int column) {
        return switch (column) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + column);
        };
    }

    public String getFlight(int indice){
        try {
            return flights.get(indice).getNumber();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight tables", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void removeRow(int row){
        this.flights.remove(row);
    }
}

