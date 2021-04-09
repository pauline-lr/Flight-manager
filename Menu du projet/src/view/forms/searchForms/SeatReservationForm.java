package view.forms.searchForms;

import javax.swing.*;
import java.awt.*;

public class SeatReservationForm extends JPanel {
    private JLabel seatCategory;
    private JComboBox seatCategorySelect;

    public SeatReservationForm() {
        this.setLayout(new GridLayout(8, 2, 5, 5));

        initForm();
    }

    public void initForm() {
        seatCategory = new JLabel("Catégorie du siège");
        seatCategory.setHorizontalAlignment(SwingConstants.RIGHT);
        add(seatCategory);
        seatCategorySelect = new JComboBox();
        this.add(seatCategorySelect);
    }
}