package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.forms.searchForms.SearchFlightsBetweenDatesForm;
import view.panels.buttons.ButtonsPanel;
import view.tables.SearchFlightsBetweenDatesTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SearchFlightsBetweenDatesPanel extends JPanel {
    private SearchFlightsBetweenDatesForm searchFlightsBetweenDatesForm;
    public SearchFlightsBetweenDatesPanel(MenuWindow menuWindow, ApplicationController controller) throws DataBaseAccessException {
        this.searchFlightsBetweenDatesForm = new SearchFlightsBetweenDatesForm();
        this.setLayout(new BorderLayout());
        this.add(searchFlightsBetweenDatesForm, BorderLayout.PAGE_START);

        ArrayList<SearchFlightsBetweenDates> flights = controller.getAllFlightsBetweenDates(searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getFirstDateCalendar());
        SearchFlightsBetweenDatesTable flightTable = new SearchFlightsBetweenDatesTable(controller, flights);
        JTable table = new JTable(flightTable);
        table.setModel(flightTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this, searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getFirstDateCalendar(), "Rechercher", controller), BorderLayout.SOUTH);
    }
}
