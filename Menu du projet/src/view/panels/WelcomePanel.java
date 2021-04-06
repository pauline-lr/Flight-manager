package view.panels;

import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JavaPanel {
    private JLabel text;

    public WelcomePanel() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        text = new JLabel("<html>" +
                "<center><h1>Bienvenue sur le gestionnaire de vols</h1>" +
                "<h4>Réalisé par Jonathan Smith et Pauline Loréa</h4></center>" +
                "</html>");
        this.setLayout(new FlowLayout());
        this.add(text);
    }
}
