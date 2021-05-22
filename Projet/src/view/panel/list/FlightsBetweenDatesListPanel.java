package view.panel.list;

import exception.dataBase.DataBaseAccessException;
import model.search.FlightsBetweenDatesSearch;
import view.table.search.FlightsBetweenDatesSearchTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlightsBetweenDatesListPanel extends JPanel {
    public FlightsBetweenDatesListPanel(ArrayList<FlightsBetweenDatesSearch> flights) throws DataBaseAccessException {
        FlightsBetweenDatesSearchTable flightsTable = new FlightsBetweenDatesSearchTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
