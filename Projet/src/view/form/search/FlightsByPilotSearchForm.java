package view.form.search;

import controller.ApplicationController;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;

public class FlightsByPilotSearchForm extends JPanel {
    private ApplicationController controller;
    private JComboBox<Object> pilotComboBox;

    public FlightsByPilotSearchForm() throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        this.setLayout(new GridLayout(4, 2));

        createFlightsByPilotForm();
    }

    public JComboBox<Object> getPilotComboBox() {
        return pilotComboBox;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void createFlightsByPilotForm() throws DataBaseConnectionException, AllDataException {
        JLabel titleLabel = new JLabel("Rechercher les vols d'un pilote");
        titleLabel.setFont(Format.titleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        JLabel pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(pilotLabel);

        pilotComboBox = new JComboBox<>(controller.getAllPilotsToString().toArray());
        this.add(pilotComboBox);
    }
}
