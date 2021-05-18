package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchFlightsByPilotForm extends JPanel {
    private ApplicationController controller;
    private JLabel pilotName;
    private JComboBox pilotNameSelect;
    private Font font = new Font(null, Font.BOLD, 13);

    public SearchFlightsByPilotForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createFlightsByPilotForm();
    }

    public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
        pilotName = new JLabel("    Choisissez le pilote");
        pilotName.setFont(font);
        pilotName.setHorizontalAlignment(SwingConstants.LEFT);
        add(pilotName);
        pilotNameSelect = new JComboBox(controller.getAllPilotsForComboBox());
        this.add(pilotNameSelect);
    }
}
