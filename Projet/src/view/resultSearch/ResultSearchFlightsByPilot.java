package view.resultSearch;

import model.SearchFlightsByPilot;
import view.tables.SearchFlightsByPilotTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchFlightsByPilot extends JPanel {
    public ResultSearchFlightsByPilot(ArrayList<SearchFlightsByPilot> flights) {
        SearchFlightsByPilotTable flightsTable = new SearchFlightsByPilotTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
