package viewPackage.panels.menuBarPanels.searchPanels;

import viewPackage.forms.searchForms.SeatReservationForm;
import viewPackage.panels.ButtonsPanel;
import viewPackage.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SeatReservationPanel extends JPanel {
    public SeatReservationPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new SeatReservationForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
