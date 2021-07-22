package view.table;

import model.search.PassengersByClassSearch;
import tool.Format;

import javax.swing.table.*;
import java.util.*;

public class PassengersByClassResultTable extends AbstractTableModel {
    private ArrayList<PassengersByClassSearch> flights;
    private ArrayList<String> columnNames;

    public PassengersByClassResultTable(ArrayList<PassengersByClassSearch> flights)  {
        setFlights(flights);
        setColumnNames();
    }

    public void setFlights(ArrayList<PassengersByClassSearch> flights) {
        this.flights = flights;
    }

    private void setColumnNames() {
        columnNames = new ArrayList<>();
        columnNames.add("Passeport");
        columnNames.add("Nom");
        columnNames.add("Prénom");
        columnNames.add("Siège");
        columnNames.add("Vol");
        columnNames.add("Heure de départ");
        columnNames.add("Date de départ");
        columnNames.add("Aéroport de départ");
        columnNames.add("Heure d'arrivée");
        columnNames.add("Date d'arrivée");
        columnNames.add("Aéroport d'arrivée");
    }

    public int getColumnCount(){
        return columnNames.size();
    }

    public int getRowCount(){
        return flights.size();
    }

    public String getColumnName(int column){
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column){
        PassengersByClassSearch flight = flights.get(row);
        return switch (column) {
            case 0 -> flight.getPassengerPassportNumber();
            case 1 -> flight.getPassengerLastName();
            case 2 -> flight.getPassengerFirstName();
            case 3 -> flight.getSeatRow() + flight.getSeatColumn();
            case 4 -> flight.getFlightNumber();
            case 5 -> Format.timeFormat(flight.getFlightDepartureTime());
            case 6 -> Format.dateFormat(flight.getFlightDepartureTime());
            case 7 -> flight.getDepartureAirportCode() + " - " + flight.getDepartureAirportName() + ", " + flight.getDepartureAirportCountry();
            case 8 -> Format.timeFormat(flight.getFlightArrivalTime());
            case 9 -> Format.dateFormat(flight.getFlightArrivalTime());
            case 10 -> flight.getArrivalAirportCode() + " - " + flight.getArrivalAirportName() + ", " + flight.getArrivalAirportCountry();
            default -> null;
        };
    }

    public Class getColumnClass(int column){
        return switch (column) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + column);
        };
    }
}
