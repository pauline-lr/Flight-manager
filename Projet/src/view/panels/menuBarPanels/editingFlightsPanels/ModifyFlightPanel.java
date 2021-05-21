package view.panels.menuBarPanels.editingFlightsPanels;


import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.flightForms.FlightForm;
import view.forms.flightForms.ModifyFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class ModifyFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private FlightForm flightForm;

    public ModifyFlightPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Modification", flightForm, "Modifier", controller), BorderLayout.SOUTH);
    }
}

