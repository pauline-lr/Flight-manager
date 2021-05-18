package view.panels.menuBarPanels.searchPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.searchForms.SearchPassengersByClassForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPassengersByClassPanel extends JPanel {
    public SearchPassengersByClassPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.setLayout(new BorderLayout());
        this.add(new SearchPassengersByClassForm(controller), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "SeatReservationSearch", null, "Rechercher", controller), BorderLayout.SOUTH);
    }
}
