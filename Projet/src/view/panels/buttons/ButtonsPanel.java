package view.panels.buttons;
import view.panels.menuBarPanels.optionsFlightPanels.AddFlightPanel;
import view.panels.menuWindowPanels.WelcomePanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    private MenuWindow menuWindow;
    private JButton retour, réinit;

    public ButtonsPanel(MenuWindow menuWindow){
        this.menuWindow = menuWindow;
        this.setLayout(new FlowLayout());
        retour = new JButton("Retour");

        réinit = new JButton("Réinitialiser");

        retour.addActionListener(new RetourListener());
        réinit.addActionListener(new RéinitListener());

        this.add(retour);
        this.add(réinit);
    }

    // bouton retour
    private class RetourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            menuWindow.getCont().removeAll();
            menuWindow.getCont().add(new WelcomePanel(), BorderLayout.CENTER);
            menuWindow.getCont().repaint();
            menuWindow.setVisible(true);
        }
    }

    // bouton de validation
    /*private class ValidationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){

        }
    }*/

    // bouton de réinitialisation
    private class RéinitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            menuWindow.getCont().removeAll();
            menuWindow.getCont().add(new AddFlightPanel(menuWindow),BorderLayout.CENTER);
            menuWindow.getCont().repaint();
            menuWindow.setVisible(true);
        }
    }
}
