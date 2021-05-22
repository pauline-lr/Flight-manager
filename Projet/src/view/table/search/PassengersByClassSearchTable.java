package view.table.search;


import controller.ApplicationController;
import model.search.PassengersByClassSearch;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PassengersByClassSearchTable extends AbstractTableModel {
    private ApplicationController controller;
    private String [] columnNames = {"N° passeport", "Nom", "Place", "N° de vol", "Heure de départ", "Heure d'arrivée",
                                    "Aéroport Départ","Aéroport Arrivée"};
    private ArrayList<PassengersByClassSearch> flights;

    public PassengersByClassSearchTable(ArrayList<PassengersByClassSearch> flights)  {
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
        PassengersByClassSearch flight = flights.get(row);
        switch(column){
            case 0:
                return flight.getPassengerPassportNumber();
            case 1:
                return flight.getPassengerLastName() + flight.getPassengerFirstName();
            case 2:
                return flight.getSeatRow() + flight.getSeatColumn();
            case 3:
                return flight.getFlightNumber();
            case 4 :
                return flight.getFlightDepartureTime();
            case 5:
                return flight.getFlightArrivalTime();
            case 6:
                return flight.getDepartureAirportCode() + flight.getDepartureAirportName() + flight.getDepartureAirportCountry();
            case 7:
                return flight.getArrivalAirportCode() + flight.getArrivalAirportName() + flight.getArrivalAirportCountry();

                default:
                    return null;
        }
    }

    public Class getColumnClass(int col){
        Class c;

        switch (col){
            case 0:
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
                c = String.class;
                break;
            case 4:
            case 5:
                c = GregorianCalendar.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + col);
        }
        return c;
    }

    public PassengersByClassSearch getPilotFlight(int indice){
        try{
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight table", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<PassengersByClassSearch> getAllSearchFlightsByPilot(){
        return flights;
    }
}
