package view.panels.menuBarPanels.searchPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.searchForms.SearchFlightsByPilotForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchFlightsByPilotPanel extends JPanel {
    public SearchFlightsByPilotPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.setLayout(new BorderLayout());
        this.add(new SearchFlightsByPilotForm(controller), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "SearchPilot", null, "Rechercher", controller), BorderLayout.SOUTH);
    }
}
