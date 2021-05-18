package view.panels.menuBarPanels.searchPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.searchForms.SearchPilotForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPilotPanel extends JPanel {
    public SearchPilotPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.setLayout(new BorderLayout());
        this.add(new SearchPilotForm(controller), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "SearchPilot", null, "Rechercher", controller), BorderLayout.SOUTH);
    }
}
