package view.panels.menuBarPanels.optionsFlightPanels;

import view.forms.flightForms.FlightForm;
import view.forms.flightForms.ModifyFlightForm;
import view.panels.ButtonsPanel;
import view.panels.menuWindowPanels.AnimationSpace;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;

public class ModifyFlightPanel extends JPanel {
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;
    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.setLayout(new BorderLayout());
        //this.add(new ModifyFlightForm(), BorderLayout.CENTER);
        this.modifyFlightForm = new ModifyFlightForm();
        modifyFlightForm.setLayout(new BorderLayout());
        modifyFlightForm.setBounds(0,120,500,100);
        this.add(modifyFlightForm);


        //this.add(new FlightForm(), BorderLayout.CENTER);

        this.flightForm = new FlightForm();
        flightForm.setLayout(new BorderLayout());
        flightForm.setBounds(140,0,500,800);
        this.add(flightForm);

        this.add(new ButtonsPanel(menuWindow), BorderLayout.SOUTH);
    }
}

