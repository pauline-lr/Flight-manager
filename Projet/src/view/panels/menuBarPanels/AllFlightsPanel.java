package view.panels.menuBarPanels;

import model.Flight;
import view.windows.MenuWindow;

import javax.swing.*;
import java.util.ArrayList;


public class AllFlightsPanel extends JPanel {
    private ArrayList<Flight> flights= new ArrayList<>();

    public AllFlightsPanel(MenuWindow menuWindow){

    }

    public synchronized ArrayList<Flight> getAllFlights() {
        return flights;
    }
}
