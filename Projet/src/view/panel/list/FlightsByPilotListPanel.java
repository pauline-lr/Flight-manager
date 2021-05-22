package view.panel.list;

import model.search.FlightsByPilotSearch;
import view.table.search.FlightsByPilotSearchTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlightsByPilotListPanel extends JPanel {
    public FlightsByPilotListPanel(ArrayList<FlightsByPilotSearch> flights) {
        FlightsByPilotSearchTable flightsTable = new FlightsByPilotSearchTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
