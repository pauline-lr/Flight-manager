package view.panel.list;

import exception.ConnectionException;
import view.table.AllFlightsListTable;

import javax.swing.*;
import java.awt.*;

public class AllFlightsListPanel extends JPanel {
    AllFlightsListTable flightsTable;

    public AllFlightsListPanel()
            throws ConnectionException {
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