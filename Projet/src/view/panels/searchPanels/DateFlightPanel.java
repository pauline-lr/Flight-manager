package view.panels.searchPanels;

import view.forms.searchForms.DateFlightForm;
import view.panels.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DateFlightPanel extends JPanel {
    public DateFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new DateFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
