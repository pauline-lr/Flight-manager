package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import view.forms.searchForms.SearchFlightsBetweenDatesForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SearchFlightsBetweenDatesPanel extends JPanel {
    private SearchFlightsBetweenDatesForm searchFlightsBetweenDatesForm;
    private BorderLayout border;
    public SearchFlightsBetweenDatesPanel(MenuWindow menuWindow, ApplicationController controller) {
        this.border = new BorderLayout();
        this.searchFlightsBetweenDatesForm = new SearchFlightsBetweenDatesForm();
        this.setLayout(border);
        this.add(searchFlightsBetweenDatesForm, BorderLayout.PAGE_START);

        this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this, searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getLastDateCalendar(), "Rechercher", controller, border), BorderLayout.SOUTH);
    }
}
