package view.resultSearch;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.tables.SearchFlightsBetweenDatesTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchFlightsBetweenDates extends JPanel {
    private ApplicationController controller;

    public ResultSearchFlightsBetweenDates(ArrayList<SearchFlightsBetweenDates> flights) throws DataBaseAccessException {
        this.controller = new ApplicationController();

        SearchFlightsBetweenDatesTable flightsTable = new SearchFlightsBetweenDatesTable(flights);
        this.setLayout(new BorderLayout());
        JTable table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
