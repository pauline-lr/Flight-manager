package view.panel.list;

import model.search.PassengersByClassSearch;
import view.table.PassengersByClassResultTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PassengersByClassResultPanel extends JPanel {
    public PassengersByClassResultPanel(ArrayList<PassengersByClassSearch> flights){
        PassengersByClassResultTable flightsTable = new PassengersByClassResultTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
