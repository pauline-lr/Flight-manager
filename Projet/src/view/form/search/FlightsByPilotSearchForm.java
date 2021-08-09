package view.form.search;

import controller.ApplicationController;
import exception.RetrievalException;
import exception.ConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;

public class FlightsByPilotSearchForm extends JPanel {
    private ApplicationController controller;
    private JComboBox<Object> pilotComboBox;

    public FlightsByPilotSearchForm() throws ConnectionException, RetrievalException {
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

    public void createFlightsByPilotForm() throws ConnectionException, RetrievalException {
        JLabel titleLabel = new JLabel("Rechercher les vols d'un pilote");
        titleLabel.setFont(Format.bigTitleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        JLabel pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setFont(Format.titleFont);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(pilotLabel);

        pilotComboBox = new JComboBox<>(controller.getAllPilotsToString().toArray());
        this.add(pilotComboBox);
    }
}
