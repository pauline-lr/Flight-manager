package view.form.search;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FlightsByPilotSearchForm extends JPanel {
    private ApplicationController controller;
    private JLabel pilotLabel;
    private JComboBox<String> pilotComboBox;

    public FlightsByPilotSearchForm() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createFlightsByPilotForm();
    }

    public JComboBox<String> getPilotComboBox() {
        return pilotComboBox;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
        pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setFont(Format.font);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(pilotLabel);

        pilotComboBox = new JComboBox<>(controller.getAllPilotsForComboBox());
        this.add(pilotComboBox);
    }
}
