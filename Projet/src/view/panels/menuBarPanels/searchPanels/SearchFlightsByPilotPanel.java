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
        SearchFlightsByPilotForm pilotForm = new SearchFlightsByPilotForm(controller);
        this.setLayout(new BorderLayout());
        this.add(pilotForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "SearchPilot", this, pilotForm.getPilotId(), "Rechercher",  controller), BorderLayout.SOUTH);
    }
}
