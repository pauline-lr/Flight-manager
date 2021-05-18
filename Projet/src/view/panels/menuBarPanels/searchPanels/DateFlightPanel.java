package view.panels.menuBarPanels.searchPanels;

import view.forms.searchForms.DateFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DateFlightPanel extends JPanel {
    private DateFlightForm dateFlightForm;
    public DateFlightPanel(MenuWindow menuWindow) {
        this.dateFlightForm = new DateFlightForm();
        this.setLayout(new BorderLayout());
        this.add(dateFlightForm, BorderLayout.PAGE_START);

        this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this, dateFlightForm.getFirstDateCalendar(),dateFlightForm.getLastDateCalendar(), "Rechercher"), BorderLayout.SOUTH);
    }
}
