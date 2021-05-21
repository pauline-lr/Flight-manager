package view.resultSearch;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.tables.SearchFlightsBetweenDatesTable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchFlightBetweenDates extends JPanel {
    private ApplicationController controller;
    private SearchFlightsBetweenDatesTable flightsTable;

    public ResultSearchFlightBetweenDates(ArrayList<SearchFlightsBetweenDates> flights) throws DataBaseAccessException {
        JTable table;
        this.controller = new ApplicationController();
        setLayout(new BorderLayout());

        if(flights == null){
            flightsTable = new SearchFlightsBetweenDatesTable(controller);
        }else{
            flightsTable = new SearchFlightsBetweenDatesTable(controller, flights);
        }

        table = new JTable(flightsTable);
        table.setModel(flightsTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
