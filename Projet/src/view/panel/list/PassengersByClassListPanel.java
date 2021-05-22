package view.panel.list;

import model.search.PassengersByClassSearch;
import view.table.search.PassengersByClassSearchTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PassengersByClassListPanel extends JPanel {
    public PassengersByClassListPanel(ArrayList<PassengersByClassSearch> flights){
        PassengersByClassSearchTable flightsTable = new PassengersByClassSearchTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
