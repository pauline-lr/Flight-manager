package userInterface.panels.menuBarPanels.searchPanels;

import userInterface.forms.searchForms.DateFlightForm;
import userInterface.panels.ButtonsPanel;
import userInterface.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DateFlightPanel extends JPanel {
    public DateFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new DateFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
