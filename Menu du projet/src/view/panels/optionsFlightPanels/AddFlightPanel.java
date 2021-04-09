package view.panels.optionsFlightPanels;

import view.forms.flightForms.AddFlightForm;
import view.panels.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AddFlightPanel extends JPanel {
    public AddFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new AddFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
