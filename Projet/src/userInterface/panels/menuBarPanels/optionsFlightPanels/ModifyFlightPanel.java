package userInterface.panels.menuBarPanels.optionsFlightPanels;

import userInterface.forms.flightForms.ModifyFlightForm;
import userInterface.panels.ButtonsPanel;
import userInterface.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class ModifyFlightPanel extends JPanel {
    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}

