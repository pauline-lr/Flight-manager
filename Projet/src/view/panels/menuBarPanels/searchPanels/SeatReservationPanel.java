package view.panels.menuBarPanels.searchPanels;

import view.forms.searchForms.SeatReservationForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class SeatReservationPanel extends JPanel {
    public SeatReservationPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        this.add(new SeatReservationForm(), BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}
