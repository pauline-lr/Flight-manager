package view.form.search;

import controller.ApplicationController;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;

public class FlightsByPilotSearchForm extends JPanel {
    private ApplicationController controller;
    private JLabel pilotLabel;
    private JComboBox pilotComboBox;

    public FlightsByPilotSearchForm() throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createFlightsByPilotForm();
    }

    public JComboBox getPilotComboBox() {
        return pilotComboBox;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void createFlightsByPilotForm() throws DataBaseConnectionException, AllDataException {
        pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setFont(Format.titleFont);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(pilotLabel);

        pilotComboBox = new JComboBox(controller.getAllPilotsToString().toArray());
        this.add(pilotComboBox);
    }
}
