package view.panel.button;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import model.Flight;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;
import view.panel.edit.AddFlightPanel;
import view.panel.edit.ModifyFlightPanel;
import view.panel.home.WelcomePanel;
import view.window.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ButtonsPanel extends JPanel {
    private ApplicationController controller;
    private static MenuWindow menuWindow;
    private String typeAction;
    private FlightForm flightForm;
    private ModifyFlightForm modifyFlightForm;
    private JButton retour, validation, réinit;


    public ButtonsPanel(String typeAction, FlightForm flightForm, String label) {
        setController(new ApplicationController());
        setMenuWindow();
        this.typeAction = typeAction;
        this.flightForm = flightForm;
        this.setLayout(new FlowLayout());

        retour = new JButton("Retour");
        validation = new JButton(label);
        réinit = new JButton("Réinitialiser");

        retour.addActionListener(new RetourListener());
        validation.addActionListener(new ValidationListener());
        réinit.addActionListener(new ResetListener());

        this.add(retour);
        this.add(validation);
        this.add(réinit);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public static void setMenuWindow() {
        ButtonsPanel.menuWindow = new MenuWindow();
    }

    // bouton retour
    public static class RetourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            menuWindow.getContainer().removeAll();
            menuWindow.getContainer().add(new WelcomePanel(), BorderLayout.CENTER);
            menuWindow.getContainer().repaint();
            menuWindow.setVisible(true);
        }
    }

    // bouton de validation
    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                if (flightForm != null) {
                    Flight flight = flightForm.getFlight();
                    if (typeAction.equals("Addition")) {
                        controller.addFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else if (typeAction.equals("Modification")) {
                        //controller.modifyFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            takeOut();
        }
    }

    public void takeOut() {
        menuWindow.getContainer().removeAll();

        if (typeAction.equals("Addition")) {
            try {
                menuWindow.getContainer().add(new AddFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else if (typeAction.equals("Modification")) {
            try {
                menuWindow.getContainer().add(new ModifyFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
        menuWindow.getContainer().repaint();
        menuWindow.setVisible(true);
    }
}


