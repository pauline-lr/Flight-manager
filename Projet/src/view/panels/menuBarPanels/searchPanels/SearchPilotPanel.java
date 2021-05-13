package view.panels.menuBarPanels.searchPanels;

import view.forms.searchForms.SearchPilotForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SearchPilotPanel extends JPanel {
    public SearchPilotPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new SearchPilotForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
