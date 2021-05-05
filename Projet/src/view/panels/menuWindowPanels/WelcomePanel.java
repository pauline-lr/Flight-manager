package view.panels.menuWindowPanels;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel text;

    public WelcomePanel() {
        text = new JLabel("<html>" +
                "<center><h1>Bienvenue sur le gestionnaire de vols</h1>" +
                "<h4>Réalisé par Pauline Loréa et Jonathan Smith</h4></center>" +
                "</html>");
        this.setLayout(new FlowLayout());
        this.add(text);
    }
}
