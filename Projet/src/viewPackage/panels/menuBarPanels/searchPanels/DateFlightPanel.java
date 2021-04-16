package viewPackage.panels.menuBarPanels.searchPanels;

import viewPackage.forms.searchForms.DateFlightForm;
import viewPackage.panels.ButtonsPanel;
import viewPackage.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DateFlightPanel extends JPanel {
    public DateFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new DateFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
