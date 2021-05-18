package view.panels.menuBarPanels.searchPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.searchForms.SeatReservationForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SeatReservationPanel extends JPanel {
    public SeatReservationPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.setLayout(new BorderLayout());
        this.add(new SeatReservationForm(controller), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "SeatReservationSearch", null, "Rechercher", controller), BorderLayout.SOUTH);
    }
}
