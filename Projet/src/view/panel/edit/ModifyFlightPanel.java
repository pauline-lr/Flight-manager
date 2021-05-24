package view.panel.edit;

import exception.dataBase.DataBaseConnectionException;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;
import view.panel.button.ButtonsPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ModifyFlightPanel extends JPanel {
    public ModifyFlightPanel() throws SQLException, DataBaseConnectionException {
        FlightForm flightForm = new FlightForm();
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(flightForm), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);
    }
}

