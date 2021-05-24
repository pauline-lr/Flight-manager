package view.panel.edit;

import exception.dataBase.DataBaseConnectionException;
import view.form.edit.FlightForm;
import view.panel.button.ButtonsPanel;
import view.window.MenuWindow;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private FlightForm flightForm;

    public AddFlightPanel() throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel("Addition", flightForm, "Ajouter"), BorderLayout.SOUTH);
    }
}
