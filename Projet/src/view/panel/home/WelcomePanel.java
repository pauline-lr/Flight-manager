package view.panel.home;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    public WelcomePanel() {
        JLabel text = new JLabel("<html>" +
                "<center><h1>Bienvenue sur le gestionnaire de vols</h1>" +
                "<h4>Réalisé par Pauline Loréa et Jonathan Smith</h4></center>" +
                "</html>");
        this.setLayout(new FlowLayout());
        this.add(text);
    }
}
