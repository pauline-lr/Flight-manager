package view.panel.list;

import exception.dataBase.DataBaseConnectionException;
import exception.FlightException;
import view.CheckEmptyResult;
import view.table.AllFlightsListTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AllFlightsListPanel extends JPanel {
    AllFlightsListTable flightsTable;

    public AllFlightsListPanel()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        setFlightsTable(new AllFlightsListTable());
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public AllFlightsListTable getFlightsTable() {
        return flightsTable;
    }

    public void setFlightsTable(AllFlightsListTable flightsTable) {
        this.flightsTable = flightsTable;
    }
}