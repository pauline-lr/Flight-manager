package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class ModifyFlightForm extends JPanel {
    private JLabel flightLabel;
    private JComboBox flightComboBox;


    public ModifyFlightForm(){
        this.setLayout(new GridLayout(15, 1, 3, 3));

        flightLabel = new JLabel("Vol à modifier : ");
        flightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(flightLabel);

        flightComboBox = new JComboBox();
        this.add(flightComboBox);
    }
}
