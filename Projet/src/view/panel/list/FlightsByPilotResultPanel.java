package view.panel.list;

import model.search.FlightsByPilotSearch;
import view.table.FlightsByPilotResultTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlightsByPilotResultPanel extends JPanel {
    public FlightsByPilotResultPanel(ArrayList<FlightsByPilotSearch> flights) {
        FlightsByPilotResultTable flightsTable = new FlightsByPilotResultTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
