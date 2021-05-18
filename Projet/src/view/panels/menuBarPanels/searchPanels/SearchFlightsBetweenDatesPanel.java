package view.panels.menuBarPanels.searchPanels;

import controller.ApplicationController;
import view.forms.searchForms.SearchFlightsBetweenDatesForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SearchFlightsBetweenDatesPanel extends JPanel {
    private SearchFlightsBetweenDatesForm searchFlightsBetweenDatesForm;
    public SearchFlightsBetweenDatesPanel(MenuWindow menuWindow, ApplicationController controller) {
        this.searchFlightsBetweenDatesForm = new SearchFlightsBetweenDatesForm();
        this.setLayout(new BorderLayout());
        this.add(searchFlightsBetweenDatesForm, BorderLayout.PAGE_START);

        this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this, searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getLastDateCalendar(), "Rechercher", controller), BorderLayout.SOUTH);
    }
}
