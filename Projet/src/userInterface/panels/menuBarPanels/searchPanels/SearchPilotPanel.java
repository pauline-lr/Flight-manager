package userInterface.panels.menuBarPanels.searchPanels;

import userInterface.forms.searchForms.SearchPilotForm;
import userInterface.panels.ButtonsPanel;
import userInterface.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SearchPilotPanel extends JPanel {
    public SearchPilotPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new SearchPilotForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
