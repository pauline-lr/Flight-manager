package view.resultSearch;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.tables.SearchFlightsBetweenDatesTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchFlightBetweenDates extends JPanel {
    private ApplicationController controller;

    public ResultSearchFlightBetweenDates(ArrayList<SearchFlightsBetweenDates> flights) throws DataBaseAccessException {
        JTable table;
        this.controller = new ApplicationController();

        /*if(flights == null){
            flightsTable = new SearchFlightsBetweenDatesTable(controller);
        }else{*/
        SearchFlightsBetweenDatesTable flightsTable = new SearchFlightsBetweenDatesTable(flights);
        this.setLayout(new BorderLayout());
        table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
