package view.resultSearch;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.panels.menuBarPanels.searchingPanels.SearchFlightsBetweenDatesPanel;
import view.tables.SearchFlightsBetweenDatesTable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class ResultSearchFlightBetweenDates extends JPanel {
    private ApplicationController controller;
    private SearchFlightsBetweenDatesPanel panel;
    private JPanel panelTable;
    private SearchFlightsBetweenDatesTable flightTable;
    RowSorter<SearchFlightsBetweenDatesTable> sorter;


    public ResultSearchFlightBetweenDates(ApplicationController controller, SearchFlightsBetweenDatesPanel panel, ArrayList<SearchFlightsBetweenDates> flights) throws DataBaseAccessException {
        JTable table;
        TableColumn column;
        JScrollPane scrollPane;

        this.controller = controller;
        setLayout(new BorderLayout());
        panelTable = new JPanel();
        panelTable.setLayout(new BorderLayout());

        if(flights == null){
            flightTable = new SearchFlightsBetweenDatesTable(controller,  flights);
        }else{
            flightTable = new SearchFlightsBetweenDatesTable(flights);
        }

        table = new JTable(flightTable);
        sorter = new TableRowSorter<>(flightTable);
        table.setRowSorter(sorter);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(300);

        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }
}
