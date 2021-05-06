package view.panels.menuBarPanels.optionsFlightPanels;


import view.forms.flightForms.FlightForm;
import view.panels.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AddFlightPanel extends JPanel {
    public AddFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new FlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
