package view.panels.menuBarPanels.listingFlightsPanels;

import controller.ApplicationController;
import model.Flight;
import model.SearchFlightsBetweenDates;
import view.tables.ListAllFlightsTable;
import view.tables.SearchFlightsBetweenDatesTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ListAllFlightsPanel extends JPanel {
    public ListAllFlightsPanel(MenuWindow menuWindow, ApplicationController controller) {
        /*ListAllFlightsTable listAllFlightsTable = new ListAllFlightsTable(controller);
        JTable table = new JTable(listAllFlightsTable);*/
        ListAllFlightsTable flightTable = new ListAllFlightsTable(controller);
        JTable table = new JTable(flightTable);
        table.setModel(flightTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}