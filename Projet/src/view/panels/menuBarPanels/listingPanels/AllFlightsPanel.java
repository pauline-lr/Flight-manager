package view.panels.menuBarPanels.listingPanels;

import model.Flight;
import view.windows.MenuWindow;

import javax.swing.*;
import java.util.ArrayList;

/*
* source : https://thierry-leriche-dessirier.developpez.com/tutoriels/java/afficher-tableau-avec-tablemodel-5-min/
* */

public class AllFlightsPanel extends JPanel {
    private ArrayList<Flight> flights= new ArrayList<>();
    private String[] columns = {"Numéro", "Départ", "Arrivée", "Repas", "Description du repas", "Pilote", "Porte de départ", "Porte d'arrivée", "Avion"};

    public AllFlightsPanel(MenuWindow menuWindow){
        try{
            // flights = controller.getAllFlights();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public AllFlightsPanel(ArrayList<Flight> flights){
        this.flights = flights;
    }

    public int getColumnCount() {
        return columns.length;
    }

    public int getRowCount() {
        return flights.size();
    }

    public String getColumns(int numCol) {
        return columns[numCol];
    }

    //***
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
                return flight.getMealDescription();
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

   /* public Class getColumnClass(int columnIndex){
        Class cl = null;
        switch (columnIndex) {
        // a continuer
            default:
                return Object.class;

        }
        return cl;
    }*/

    public Flight getFlight(int indColumn){
        try{
            return flights.get(indColumn);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
