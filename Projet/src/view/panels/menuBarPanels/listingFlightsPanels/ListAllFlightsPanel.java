package view.panels.menuBarPanels.listingFlightsPanels;

import exception.DataBaseConnectionException;
import exception.FlightException;
import view.tables.ListAllFlightsTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;



public class ListAllFlightsPanel extends JPanel {
    public ListAllFlightsPanel()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        ListAllFlightsTable flightsTable = new ListAllFlightsTable();
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}