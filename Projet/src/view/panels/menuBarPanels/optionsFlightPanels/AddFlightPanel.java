package view.panels.menuBarPanels.optionsFlightPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.flightForms.FlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private FlightForm flightForm;

    public AddFlightPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Addition", flightForm, "Ajouter", controller), BorderLayout.SOUTH);
    }
}
