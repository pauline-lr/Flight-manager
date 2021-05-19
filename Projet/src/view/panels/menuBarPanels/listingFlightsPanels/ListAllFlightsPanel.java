package view.panels.menuBarPanels.listingFlightsPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import model.SearchFlightsBetweenDates;
import view.tables.ListAllFlightsTable;
import view.tables.SearchFlightsBetweenDatesTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class ListAllFlightsPanel extends JPanel {
    public ListAllFlightsPanel(MenuWindow menuWindow, ApplicationController controller)
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        /*ListAllFlightsTable listAllFlightsTable = new ListAllFlightsTable(controller);
        JTable table = new JTable(listAllFlightsTable);*/
        ListAllFlightsTable flightsTable = new ListAllFlightsTable();
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}