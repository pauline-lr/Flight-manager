package view.panel.list;

import model.search.FlightsBetweenDatesSearch;
import view.table.FlightsBetweenDatesResultTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlightsBetweenDatesResultPanel extends JPanel {
    public FlightsBetweenDatesResultPanel(ArrayList<FlightsBetweenDatesSearch> flights)
            throws DataBaseAccessException {
        FlightsBetweenDatesResultTable flightsTable = new FlightsBetweenDatesResultTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
