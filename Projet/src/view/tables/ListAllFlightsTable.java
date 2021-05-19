package view.tables;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import model.Flight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.*;

public class ListAllFlightsTable extends AbstractTableModel {
    private ApplicationController controller;
    private String [] columnNames;
    private ArrayList<Flight> flights;

    public ListAllFlightsTable(ApplicationController controller){
        this.columnNames = new String[]{"Numéro", "Heure de départ", "Aéroport de départ", "Porte de départ",
                "Heure d'arrivée", "Aéroport d'arrivée", "Porte d'arrivée", "Repas", "Description du repas", "Pilote", "Avion"};
        this.controller = controller;
        flights = controller.getAllFlights();
    }

    public ListAllFlightsTable(ArrayList<Flight> flights){
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
        Flight flight = flights.get(row);
        switch(column){
            case 0:
                return flight.getNumber();
            case 1:
                return flight.getDepartureTime().getTime();
            case 2:
                try {
                    return controller.getAirportToString(flight.getDepartureGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 3:
                return flight.getDepartureGate();
            case 4:
                return flight.getArrivalTime().getTime();
            case 5 :
                try {
                    return controller.getAirportToString(flight.getArrivalGate());
                } catch (SQLException | DataBaseConnectionException throwables) {
                    throwables.printStackTrace();
                }
            case 6 :
                return flight.getArrivalGate();
            case 7:
                return flight.getMealOnBoard() ? "Oui" : "Non";
            case 8 :
                return flight.getMealDescription() == null ? null : flight.getMealDescription();
            case 9:
                return flight.getPilot();
            case 10:
                return flight.getPlane();
            default:
                return null;
        }
    }

    public Class getColumnClass(int col){
        Class c;

        switch (col){
            case 0:
            case 2:
            case 5:
            case 7:
                case 8:
                    case 9:
            case 3:
            case 6:
                c = String.class;
                break;
            case 1:
            case 4:
                c = GregorianCalendar.class;
                break;
            case 10:
                c = Integer.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + col);
        }
        return c;
    }

    public Flight getFlight(int indice){
        try{
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight tables", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<Flight> getAllFlights(){
        return flights;
    }

    public void removeRow(int row){
        this.flights.remove(row);
    }
}
