package view.panels.menuBarPanels.optionsFlightPanels;


import view.forms.flightForms.FlightForm;
import view.forms.flightForms.ModifyFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;


public class ModifyFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private FlightForm flightForm;

    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.LINE_START);
        this.add(new ButtonsPanel(menuWindow, "Modification", flightForm, "Modifier"), BorderLayout.SOUTH);
    }
}

