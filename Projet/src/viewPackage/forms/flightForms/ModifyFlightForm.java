package viewPackage.forms.flightForms;

import javax.swing.*;
import java.awt.*;

public class ModifyFlightForm extends JPanel {
    private JLabel flight;
    private JComboBox numberFlight;

    public ModifyFlightForm(){
        this.setLayout(new GridLayout(10, 5));

        flight = new JLabel("Choisissez le vol à modifier ");
        flight.setHorizontalAlignment(SwingConstants.RIGHT);
        add(flight);
        numberFlight = new JComboBox();
        this.add(numberFlight);

        // faire apparaitre dans les cases du formulaire les données de la ligne de la tbale vol choisie

    }
}
