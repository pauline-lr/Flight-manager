package view.panels.menuBarPanels.optionsFlightPanels;

import controller.ApplicationController;
import view.forms.flightForms.DeleteFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class DeleteFlightPanel extends JPanel {
    public DeleteFlightPanel(MenuWindow menuWindow, ApplicationController controller) {
        this.setLayout(new BorderLayout());
        this.add(new DeleteFlightForm(), BorderLayout.CENTER);
    }
}
