package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;

public class DeleteFlightForm extends JPanel {
    private JLabel flight;
    private JComboBox numberFlight;

    public DeleteFlightForm(){
        this.setLayout(new GridLayout(15, 1, 3, 3));

        flight = new JLabel("Choisissez le vol à supprimer ");
        flight.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(flight);

        numberFlight = new JComboBox();
        this.add(numberFlight);
    }
}
