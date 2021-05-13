package view.panels.menuBarPanels.optionsFlightPanels;


import view.forms.flightForms.FlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class AddFlightPanel extends JPanel {
    public AddFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new FlightForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }

    public static class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }
}
