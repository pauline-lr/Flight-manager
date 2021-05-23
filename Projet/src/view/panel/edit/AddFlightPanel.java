package view.panel.edit;

import exception.dataBase.DataBaseConnectionException;
import view.form.edit.FlightForm;
import view.panel.button.ButtonsPanel;
import view.window.MenuWindow;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private MenuWindow menuWindow;
    private FlightForm flightForm;

    public AddFlightPanel() throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Addition", flightForm, "Ajouter"), BorderLayout.SOUTH);
    }
}
