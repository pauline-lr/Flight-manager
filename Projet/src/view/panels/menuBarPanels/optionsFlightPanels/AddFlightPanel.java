package view.panels.menuBarPanels.optionsFlightPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.flightForms.AddFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private AddFlightForm addFlightForm;

    public AddFlightPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.addFlightForm = new AddFlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(addFlightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Addition", addFlightForm, "Ajouter", controller), BorderLayout.SOUTH);
    }
}
