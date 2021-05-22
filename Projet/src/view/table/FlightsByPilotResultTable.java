package view.table;

import controller.ApplicationController;
import model.search.FlightsByPilotSearch;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightsByPilotResultTable extends AbstractTableModel {
    private ApplicationController controller;
    private String [] columnNames = {"N° de vol", "Heure de départ", "Heure d'arrivée",
            "Avion", "Aéroport Départ", "Aéroport Arrivée"};

    private ArrayList<FlightsByPilotSearch> flights;

    public FlightsByPilotResultTable(ArrayList<FlightsByPilotSearch> flights)  {
        setController(new ApplicationController());
        this.flights = flights;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return flights.size();
    }

    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int column){
        FlightsByPilotSearch flight = flights.get(row);
        switch(column){
            case 0:
                return flight.getFlightNumber();
            case 1:
                return flight.getFlightDepartureTime();
            case 2:
                return flight.getFlightArrivalTime();
            case 3:
                return flight.getPlaneId().toString() + flight.getPlaneModel() + flight.getPlaneBrand();
            case 4 :
                return flight.getDepartureAirportCode() + flight.getDepartureAirportName() + flight.getDepartureAirportCountry();
            case 5:
                return flight.getArrivalAirportCode() + flight.getArrivalAirportName() + flight.getArrivalAirportCountry();
            default:
                return null;
        }
    }

    public Class getColumnClass(int col){
        Class c;

        switch (col){
            case 0:
            case 3:
            case 4:
            case 5:
                c = String.class;
                break;
            case 1:
            case 2:
                c = GregorianCalendar.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + col);
        }
        return c;
    }

    public FlightsByPilotSearch getPilotFlight(int indice){
        try{
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight table", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<FlightsByPilotSearch> getAllSearchFlightsByPilot(){
        return flights;
    }
}
