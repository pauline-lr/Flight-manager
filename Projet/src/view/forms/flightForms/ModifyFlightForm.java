package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class ModifyFlightForm extends JPanel {
    private Font font = new Font(null, Font.BOLD, 13);
    private JLabel flightLabel;
    private JComboBox flightComboBox;


    public ModifyFlightForm(){
        this.setLayout(new GridLayout(4, 1, 3, 3));

        flightLabel = new JLabel("    Vol à modifier");
        flightLabel.setFont(font);
        flightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightLabel);

        flightComboBox = new JComboBox();
        this.add(flightComboBox);
    }
}
