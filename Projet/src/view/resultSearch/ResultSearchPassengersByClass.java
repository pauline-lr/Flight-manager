package view.resultSearch;

import model.SearchPassengersByClass;
import view.tables.SearchPassengersByClassTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchPassengersByClass extends JPanel {
    public ResultSearchPassengersByClass(ArrayList<SearchPassengersByClass> flights){
        SearchPassengersByClassTable flightsTable = new SearchPassengersByClassTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
