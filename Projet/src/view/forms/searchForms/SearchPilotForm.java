package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPilotForm extends JPanel {
    private ApplicationController controller;
    private JLabel pilotName;
    private JComboBox pilotNameSelect;

    public SearchPilotForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(8, 2, 5, 5));

        initForm();
    }

    public void initForm() throws SQLException, DataBaseConnectionException {
        pilotName = new JLabel("Pilote : ");
        pilotName.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pilotName);
        pilotNameSelect = new JComboBox(controller.getAllPilotsForComboBox());
        this.add(pilotNameSelect);
    }
}
