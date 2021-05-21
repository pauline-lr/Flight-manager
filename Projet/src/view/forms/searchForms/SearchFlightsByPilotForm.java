package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFlightsByPilotForm extends JPanel {
    private ApplicationController controller;
    private final Font font = new Font(null, Font.BOLD, 13);

    private JLabel pilotLabel;
    private JComboBox pilotComboBox;

    public SearchFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
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

    public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
        pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setFont(font);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(pilotLabel);

        pilotComboBox = new JComboBox(controller.getAllPilotsForComboBox());
        this.add(pilotComboBox);
    }
}
