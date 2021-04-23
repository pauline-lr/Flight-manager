package view.panels.menuBarPanels.optionsFlightPanels;

import view.forms.flightForms.ModifyFlightForm;
import view.panels.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class ModifyFlightPanel extends JPanel {
    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}

