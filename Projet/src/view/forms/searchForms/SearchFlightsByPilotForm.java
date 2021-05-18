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

    public SearchFlightsByPilotForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(8, 2, 5, 5));

        createFlightsByPilotForm();
    }

    public void createFlightsByPilotForm() throws SQLException, DataBaseConnectionException {
        pilotName = new JLabel("Pilote : ");
        pilotName.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pilotName);
        pilotNameSelect = new JComboBox(controller.getAllPilotsForComboBox());
        this.add(pilotNameSelect);
    }
}
