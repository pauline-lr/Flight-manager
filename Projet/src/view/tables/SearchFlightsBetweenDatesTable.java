package view.tables;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchFlightsBetweenDatesTable extends AbstractTableModel {
    private ApplicationController controller;
    private String [] columnNames = {"N° de vol", "Heure de départ", "Heure d'arrivée",
            "Terminal Départ", "N° de porte Départ", "Aéroport Départ",
            "Terminal Arrivée", "N° de porte Arrivée", "Aéroport Arrivée",
            "Avion", "Pilot"};

    private ArrayList<SearchFlightsBetweenDates> flights;

    public SearchFlightsBetweenDatesTable(ApplicationController controller) throws DataBaseAccessException {
        this.controller = controller;
    }

    public SearchFlightsBetweenDatesTable(ApplicationController controller, ArrayList<SearchFlightsBetweenDates> flights){
        this.flights = flights;
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
        SearchFlightsBetweenDates flight = flights.get(row);

        switch(column){
            case 0:
                return flight.getFlightNumber();
            case 1:
                return flight.getFlightDepartureTime();
            case 2:
                return flight.getFlightArrivalTime();
            case 3:
                return flight.getDepartureGateTerminal();
            case 4:
                return flight.getDepartureGateNumber();
            case 5 :
                return flight.getDepartureAirportCode() + flight.getDepartureAirportName() + flight.getDepartureAirportCountry();
            case 6:
                return flight.getArrivalGateTerminal();
            case 7:
                return flight.getArrivalGateNumber();
            case 8 :
                return flight.getArrivalAirportCode() + flight.getArrivalAirportName() + flight.getArrivalAirportCountry();
            case 9:
                return flight.getPlaneId().toString() + flight.getPlaneModel() + flight.getPlaneBrand();
            case 10:
                return flight.getPilotLicenceNumber() + flight.getPilotFirstName() + flight.getPilotLastName();
            default:
                return null;
        }
    }

    public Class getColumnClass(int col){
        Class c;

        switch (col){
            case 0:
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
                c = String.class;
                break;
            case 1:
            case 2:
                c = GregorianCalendar.class;
                break;
            case 4:
            case 7:
                c = Integer.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + col);
        }
        return c;
    }

    public SearchFlightsBetweenDates getDateFlight(int indice){
        try{
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight tables", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<SearchFlightsBetweenDates> getAllSearchFlightsByDate(){
        return flights;
    }
}
