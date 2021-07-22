package view.table;

import model.search.FlightsByPilotSearch;
import tool.Format;

import javax.swing.table.*;
import java.util.*;

public class FlightsByPilotResultTable extends AbstractTableModel {
    private ArrayList<FlightsByPilotSearch> flights;
    private ArrayList<String> columnNames;

    public FlightsByPilotResultTable(ArrayList<FlightsByPilotSearch> flights)  {
        setFlights(flights);
        setColumnNames();
    }

    public void setFlights(ArrayList<FlightsByPilotSearch> flights) {
        this.flights = flights;
    }

    private void setColumnNames() {
        columnNames = new ArrayList<>();
        columnNames.add("Numéro");
        columnNames.add("Avion");
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
        FlightsByPilotSearch flight = flights.get(row);
        return switch (column) {
            case 0 -> flight.getFlightNumber();
            case 1 -> flight.getPlaneId().toString() + " - " + flight.getPlaneBrand() + " " + flight.getPlaneModel();
            case 2 -> Format.timeFormat(flight.getFlightDepartureTime());
            case 3 -> Format.dateFormat(flight.getFlightDepartureTime());
            case 4 -> flight.getDepartureAirportCode() + " - " + flight.getDepartureAirportName() + ", " + flight.getDepartureAirportCountry();
            case 5 -> Format.timeFormat(flight.getFlightArrivalTime());
            case 6 -> Format.dateFormat(flight.getFlightArrivalTime());
            case 7 -> flight.getArrivalAirportCode() + " - " + flight.getArrivalAirportName() + ", " + flight.getArrivalAirportCountry();
            default -> null;
        };
    }

    public Class getColumnClass(int col){
        return switch (col) {
            case 0, 1, 2, 3, 4, 5, 6, 7 -> String.class;
            default -> throw new IllegalStateException("Unexpected value: " + col);
        };
    }
}
