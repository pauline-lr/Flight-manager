package view.form.edit;

import tool.Format;

import javax.swing.*;

public class AddFlightForm extends JPanel {
    public AddFlightForm() {
        JLabel addFlightLabel = new JLabel("Ajouter un vol");
        addFlightLabel.setFont(Format.titleFont);
        addFlightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(addFlightLabel);
    }
}
