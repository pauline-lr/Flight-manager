package view.tables;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
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
        columnNames.add("Porte de départ");
        columnNames.add("Heure d'arrivée");
        columnNames.add("Date d'arrivée");
        columnNames.add("Aéroport d'arrivée");
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
                return flight.getPilot();
            case 2:
                return flight.getPlane();
            case 3:
                return departure.get(Calendar.HOUR_OF_DAY) + ":" + departure.get(Calendar.MINUTE);
            case 4:
                return departure.get(Calendar.DAY_OF_MONTH) + "/" + departure.get(Calendar.MONTH) + "/" + departure.get(Calendar.YEAR);
            case 5:
                try {
                    return controller.getAirportToString(flight.getDepartureGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 6:
                return flight.getDepartureGate();
            case 7:
                return arrival.get(Calendar.HOUR_OF_DAY) + ":" + arrival.get(Calendar.MINUTE);
            case 8:
                return arrival.get(Calendar.DAY_OF_MONTH) + "/" + arrival.get(Calendar.MONTH) + "/" + arrival.get(Calendar.YEAR);
            case 9:
                try {
                    return controller.getAirportToString(flight.getArrivalGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 10:
                return flight.getArrivalGate();
            case 11:
                return flight.getMealOnBoard() ? "Oui" : "Non";
            case 12:
                return flight.getMealDescription() == null ? null : flight.getMealDescription();
            default:
                return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c = switch (column) {
            case 0, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> String.class;
            case 2 -> Integer.class;
            default -> throw new IllegalStateException("Unexpected value: " + column);
        };

        return c;
    }

    public Flight getFlight(int indice){
        try {
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight tables", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void removeRow(int row){
        this.flights.remove(row);
    }
}

