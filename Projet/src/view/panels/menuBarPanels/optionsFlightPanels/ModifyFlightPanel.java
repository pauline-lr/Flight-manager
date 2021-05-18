package view.panels.menuBarPanels.optionsFlightPanels;


import controller.ApplicationController;
import exception.DataBaseConnectionException;
import view.forms.flightForms.AddFlightForm;
import view.forms.flightForms.ModifyFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class ModifyFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private AddFlightForm addFlightForm;

    public ModifyFlightPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.addFlightForm = new AddFlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.PAGE_START);
        this.add(addFlightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Modification", addFlightForm, "Modifier", controller), BorderLayout.SOUTH);
    }
}

