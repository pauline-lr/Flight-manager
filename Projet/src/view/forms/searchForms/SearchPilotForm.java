package view.forms.searchForms;

import javax.swing.*;
import java.awt.*;

public class SearchPilotForm extends JPanel {
    private JLabel pilotName;
    private JComboBox pilotNameSelect;

    public SearchPilotForm(){
        this.setLayout(new GridLayout(8, 2, 5, 5));

        initForm();
    }

    public void initForm() {
        pilotName = new JLabel("Nom du pilote : ");
        pilotName.setHorizontalAlignment(SwingConstants.RIGHT);
        add(pilotName);
        pilotNameSelect = new JComboBox();
        this.add(pilotNameSelect);
    }
}
