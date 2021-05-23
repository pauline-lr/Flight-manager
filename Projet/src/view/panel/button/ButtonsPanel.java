package view.panel.button;

import controller.ApplicationController;
import exception.dataBase.DataBaseAccessException;
import exception.dataBase.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import view.form.edit.FlightForm;
import view.panel.edit.AddFlightPanel;
import view.panel.edit.ModifyFlightPanel;
import view.panel.home.WelcomePanel;
import view.panel.search.FlightsBetweenDatesSearchPanel;
import view.panel.search.FlightsByPilotSearchPanel;
import view.panel.search.PassengersByClassSearchPanel;
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
    private JButton retour, validation, réinit;


    public ButtonsPanel(MenuWindow menuWindow, String typeAction, FlightForm flightForm, String label){
        setController(new ApplicationController());
        this.menuWindow = menuWindow;
        this.typeAction = typeAction;
        this.flightForm = flightForm;
        this.setLayout(new FlowLayout());

        retour = new JButton("Retour");
        validation =  new JButton(label);
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


    // bouton retour
    public static class RetourListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            menuWindow.getCont().removeAll();
            menuWindow.getCont().add(new WelcomePanel(), BorderLayout.CENTER);
            menuWindow.getCont().repaint();
            menuWindow.setVisible(true);
        }
    }

    // bouton de validation
    private class ValidationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            try {
                if(flightForm != null) {
                    Flight flight = flightForm.getFlight();
                    if (typeAction.equals("Addition")) {
                        controller.addFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    // Modification
                    //Flight flight = modifyFlight.getFlight()
                    //controller.modifyFlight(flight);
                    JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (SQLException | FlightException.NumberFlightException | FlightException.MealDescriptionException |
                DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // bouton de réinitialisation
    private class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            try {
                takeOut();
            } catch (SQLException | DataBaseConnectionException | DataBaseAccessException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void takeOut() throws SQLException, DataBaseConnectionException, DataBaseAccessException {
        menuWindow.getCont().removeAll();

        if (typeAction.equals("Addition")) {
            menuWindow.getCont().add(new AddFlightPanel(menuWindow), BorderLayout.CENTER);
        } else if (typeAction.equals("Modification")) {
            menuWindow.getCont().add(new ModifyFlightPanel(menuWindow), BorderLayout.CENTER);
        } else if (typeAction.equals("DateFlightSearch")) {
            menuWindow.getCont().add(new FlightsBetweenDatesSearchPanel(), BorderLayout.CENTER);
        } else if(typeAction.equals("SearchPilot")) {
            menuWindow.getCont().add(new FlightsByPilotSearchPanel(menuWindow), BorderLayout.CENTER);
        } else if(typeAction.equals("SeatReservationSearch")) {
            menuWindow.getCont().add(new PassengersByClassSearchPanel(menuWindow), BorderLayout.CENTER);
        }

        menuWindow.getCont().repaint();
        menuWindow.setVisible(true);
    }
}


