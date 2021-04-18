package viewPackage.panels.menuBarPanels.searchPanels;

import viewPackage.forms.searchForms.SearchPilotForm;
import viewPackage.panels.ButtonsPanel;
import viewPackage.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SearchPilotPanel extends JPanel {
    public SearchPilotPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new SearchPilotForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
