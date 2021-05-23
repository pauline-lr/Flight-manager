package view.table;

import model.search.FlightsBetweenDatesSearch;
import tool.Format;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightsBetweenDatesResultTable extends AbstractTableModel {
    private ArrayList<FlightsBetweenDatesSearch> flights;
    private ArrayList<String> columnNames;


    public FlightsBetweenDatesResultTable(ArrayList<FlightsBetweenDatesSearch> flights) {
        setFlights(flights);
        setColumnNames();
    }

    public void setFlights(ArrayList<FlightsBetweenDatesSearch> flights) {
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

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        FlightsBetweenDatesSearch flight = flights.get(row);
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
                return Format.timeFormat(departure);
            case 4:
                return Format.dateFormat(departure);
            case 5:
                return flight.getDepartureAirportCode() + " - " + flight.getDepartureAirportCode()
                        + " - " + flight.getDepartureAirportCountry();
            case 6:
                return flight.getDepartureGateTerminal();
            case 7:
                return flight.getDepartureGateNumber();
            case 8:
                return Format.timeFormat(arrival);
            case 9:
                return Format.dateFormat(arrival);
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

    public Class getColumnClass(int column) {
        return switch (column) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + column);
        };
    }
}
