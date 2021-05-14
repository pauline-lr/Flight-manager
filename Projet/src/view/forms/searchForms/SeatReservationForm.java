package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SeatReservationForm extends JPanel {
    private ApplicationController controller = new ApplicationController();
    private JLabel seatCategory;
    private JComboBox seatCategorySelect;

    public SeatReservationForm() throws SQLException, DataBaseConnectionException {
        this.setLayout(new GridLayout(8, 2, 5, 5));

        initForm();
    }

    public void initForm() throws SQLException, DataBaseConnectionException {
        seatCategory = new JLabel("Classe du siège : ");
        seatCategory.setHorizontalAlignment(SwingConstants.RIGHT);
        add(seatCategory);
        seatCategorySelect = new JComboBox(controller.getAllClassesName().toArray());
        this.add(seatCategorySelect);
    }
}