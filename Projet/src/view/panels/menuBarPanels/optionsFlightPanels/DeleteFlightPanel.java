package view.panels.menuBarPanels.optionsFlightPanels;

import view.forms.flightForms.DeleteFlightForm;
import view.panels.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DeleteFlightPanel extends JPanel {
    public DeleteFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new DeleteFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}