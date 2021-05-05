package view.forms.flightForms;

import javax.swing.*;
import java.awt.*;

public class DeleteFlightForm extends JPanel {
    private JLabel text;

    public DeleteFlightForm(){
        this.setLayout(new GridLayout(15, 1, 3, 3));

        text = new JLabel("<html>" +
                "<center>Sélectionnez le(s) vol(s) à supprimer</center>" +
                "</html>");
        this.setLayout(new FlowLayout());
        this.add(text);

        // Affichage d'une JTable
    }
}
