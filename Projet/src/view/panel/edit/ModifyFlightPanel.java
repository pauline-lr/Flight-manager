package view.panel.edit;

import exception.dataBase.DataBaseConnectionException;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;
import view.panel.button.ButtonsPanel;
import view.window.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class ModifyFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private FlightForm flightForm;

    public ModifyFlightPanel(MenuWindow menuWindow) throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Modification", flightForm, "Modifier"), BorderLayout.SOUTH);
    }
}

