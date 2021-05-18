package view.forms.flightForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class ModifyFlightForm extends JPanel {
    private ApplicationController controller = new ApplicationController();
    private Font font = new Font(null, Font.BOLD, 13);
    private JLabel flightLabel;
    private JComboBox flightComboBox;


    public ModifyFlightForm() throws SQLException, DataBaseConnectionException {
        this.setLayout(new GridLayout(2, 1, 3, 3));

        flightLabel = new JLabel("    Vol à modifier");
        flightLabel.setFont(font);
        flightLabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(flightLabel);

        flightComboBox = new JComboBox(controller.getAllFlightsForComboBox());
        this.add(flightComboBox);
    }
}
