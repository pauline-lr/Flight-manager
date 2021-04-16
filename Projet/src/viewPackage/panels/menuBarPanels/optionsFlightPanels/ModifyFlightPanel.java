package viewPackage.panels.menuBarPanels.optionsFlightPanels;

import viewPackage.forms.flightForms.ModifyFlightForm;
import viewPackage.panels.ButtonsPanel;
import viewPackage.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class ModifyFlightPanel extends JPanel {
    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}

