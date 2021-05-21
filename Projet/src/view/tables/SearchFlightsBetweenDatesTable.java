package view.tables;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import exception.DataBaseConnectionException;
import model.Flight;
import model.SearchFlightsBetweenDates;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchFlightsBetweenDatesTable extends AbstractTableModel {
    private ApplicationController controller = new ApplicationController();
    private ArrayList<String> columnNames;
    private ArrayList<SearchFlightsBetweenDates> flights;

    /*public SearchFlightsBetweenDatesTable(ApplicationController controller) throws DataBaseAccessException {
        this.controller = controller;
    }*/

    public SearchFlightsBetweenDatesTable(ArrayList<SearchFlightsBetweenDates> flights) {
        setColumnNames();
        this.flights = flights;
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
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return flights.size();
    }

    public String getColumnName(int col) {
        return columnNames.get(col);
    }

    public Object getValueAt(int row, int column) {
        SearchFlightsBetweenDates flight = flights.get(row);
        GregorianCalendar departure = flight.getFlightDepartureTime();
        GregorianCalendar arrival = flight.getFlightArrivalTime();
        switch (column) {
            case 0:
                return flight.getFlightNumber();
            case 1:
                return flight.getPilotLicenceNumber() + " - " + flight.getPilotLastName()
                        + " - " + flight.getPilotFirstName();
            case 2:
                return flight.getPlaneId() + " - " + flight.getPlaneBrand()
                        + " - " + flight.getPlaneModel();
            case 3:
                return timeFormat(departure);
            case 4:
                return dateFormat(departure);
            case 5:
              return flight.getDepartureAirportCode() + " - " + flight.getDepartureAirportCode()
                      + " - " + flight.getDepartureAirportCountry();
            case 6:
                return flight.getDepartureGateTerminal();
            case 7:
               return flight.getDepartureGateNumber();
            case 8:
                return timeFormat(arrival);
            case 9:
                return dateFormat(arrival);
            case 10:
                return flight.getArrivalAirportCode() + " - " + flight.getArrivalAirportCode()
                        + " - " + flight.getArrivalAirportCountry();
            case 11:
                return flight.getArrivalGateTerminal();
            case 12:
               return flight.getArrivalGateNumber();
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
}
