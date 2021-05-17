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
    private String [] columnNames = {"Numéro", "Heure de départ", "Aéroport de départ",  "Porte de départ", "Heure d'arrivée","Aéroport d'arrivée",  "Porte d'arrivée" ,"Repas", "Description du repas", "Pilote", "Avion"};

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
                // comment accéder à l'aéroport via l'id de gate ?
                return flight.getDepartureGate().getAirport();
            case 3:
                return flight.getDepartureGate();
            case 4:
                return flight.getArrivalTime();
            case 5 :
                // comment accéder à l'aéroport via l'id de gate ?
                return flight.getArrivalGate().getAiport();
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
        Class c = null;

        switch (col){
            case 0:
            case 2:
            case 5:
            case 7:
                case 8:
                    case 9:
                c = String.class;
                break;
            case 1:
            case 4:
                c = GregorianCalendar.class;
                break;
            case 3:
            case 6:
                c = Character.class;
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
            JOptionPane.showMessageDialog(null, e, "ERROR : flight table", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public ArrayList<Flight> getAllFlights(){
        return flights;
    }
}

