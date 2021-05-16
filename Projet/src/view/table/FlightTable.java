package view.table;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.Flight;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/*
 * source : https://thierry-leriche-dessirier.developpez.com/tutoriels/java/afficher-tableau-avec-tablemodel-5-min/
 * */

public class FlightTable extends AbstractTableModel {
    private String [] columnNames = {"Numéro", "Départ", "Arrivée", "Repas", "Description du repas", "Pilote", "Porte de départ", "Porte d'arrivée", "Avion"};

    private ArrayList<Flight> flights;

    public FlightTable(ApplicationController controller){
        try{
            // je pense qu'il va falloir créer une nouvelle méthode dans DBAccess pour gérer ça
            flights = controller.getAllFlightsForComboBox();
        }catch(DataBaseAccessException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public FlightTable(ArrayList<Flight> flights){
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
                return flight.getDepartureTime();
            case 2:
                return flight.getArrivalTime();
            case 3:
                return flight.getMealOnBoard() ? "Oui" : "Non";
            case 4:
                return flight.getMealDescription() == null ? null : flight.getMealDescription();
            case 5:
                return flight.getPilot();
            case 6:
                return flight.getDepartureGate();
            case 7:
                return flight.getArrivalGate();
            case 8:
                return flight.getPlane();
            default:
                return null;
        }
    }

    public Class getColumnClass(int col){
        Class c = null;

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
            case 6:
            case 7:
                c = Character.class;
                break;
            case 8:
                c = Integer.class;
                break;
        }
        return c;
    }

    public Flight getFlight(int indice){
        try{
            return flights.get(indice);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR : flight table", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<Flight> getAllFlights(){
        return flights;
    }
}

