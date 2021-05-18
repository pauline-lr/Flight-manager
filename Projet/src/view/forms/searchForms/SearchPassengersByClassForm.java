package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPassengersByClassForm extends JPanel {
    private ApplicationController controller;
    private JLabel seatCategory;
    private JComboBox seatCategorySelect;
    private Font font = new Font(null, Font.BOLD, 13);

    public SearchPassengersByClassForm(ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(8, 2, 5, 5));

        createPassengersByClassForm();
    }

    public void createPassengersByClassForm() throws SQLException, DataBaseConnectionException {
        seatCategory = new JLabel("    Classe du siège");
        seatCategory.setFont(font);
        seatCategory.setHorizontalAlignment(SwingConstants.LEFT);
        add(seatCategory);
        seatCategorySelect = new JComboBox(controller.getAllClassesForComboBox());
        this.add(seatCategorySelect);
    }
}