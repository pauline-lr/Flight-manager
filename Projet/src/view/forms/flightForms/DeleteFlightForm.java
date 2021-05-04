package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;

public class DeleteFlightForm extends JPanel {
    private JLabel flightLabel;
    private JComboBox flightComboBox;

    public DeleteFlightForm(){
        this.setLayout(new GridLayout(15, 1, 3, 3));

        flightLabel = new JLabel("Vol à supprimer : ");
        flightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(flightLabel);

        flightComboBox = new JComboBox();
        this.add(flightComboBox);
    }
}
