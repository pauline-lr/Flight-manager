package viewPackage.panels.menuBarPanels.optionsFlightPanels;

import viewPackage.forms.flightForms.AddFlightForm;
import viewPackage.panels.ButtonsPanel;
import viewPackage.windows.MenuWindow;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class AddFlightPanel extends JPanel {
    public AddFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new AddFlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
