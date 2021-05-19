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
    private JLabel pilotLabel;
    private JComboBox pilotComboBox;
    private Font font = new Font(null, Font.BOLD, 13);

    public SearchFlightsByPilotForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createFlightsByPilotForm();
    }

    public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
        pilotLabel = new JLabel("    Choisissez le pilote");
        pilotLabel.setFont(font);
        pilotLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(pilotLabel);

        pilotComboBox = new JComboBox(controller.getAllPilotsForComboBox());
        this.add(pilotComboBox);
    }

    public String getPilotId() {
        String pilotText = (String) pilotComboBox.getSelectedItem();
        return getId(pilotText);
    }

    public String getId(String text) {
        Pattern pattern = Pattern.compile("^\\w+(?=\\s-\\s)");
        Matcher matcher = pattern.matcher(text);
        String id = null;
        if (matcher.find()) {
            id = matcher.group();
        }
        return id;
    }
}
