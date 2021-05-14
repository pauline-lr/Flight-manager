package view.panels.menuBarPanels.optionsFlightPanels;


import controller.ApplicationController;
import exception.FlightException;
import model.Flight;
import view.forms.flightForms.FlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private ApplicationController controller;
    private MenuWindow menuWindow;
    private JButton retour, validation, réinit;
    private FlightForm flightForm;

    public AddFlightPanel(MenuWindow menuWindow) {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        this.add(new ButtonsPanel(menuWindow, "Addition", flightForm), BorderLayout.SOUTH);
    }


    // bouton de validation
    /*private class ValidationListener(Flight flightForm) implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
          Flight flight = flightForm.getFlight();
            try {
                flight = flightForm.getFlight();
                controller.addFlight(flight);
                JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                takeOut();
            } catch (FlightException.NumberFlightException e) {
                e.printStackTrace();
            } catch (FlightException.MealDescriptionException e) {
                e.printStackTrace();
            }
        }
    }*/

    // bouton de réinitialisation
   /* private class RéinitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            takeOut();
        }
    }

    public void takeOut(){
        menuWindow.getCont().removeAll();
        menuWindow.getCont().add(new AddFlightPanel(menuWindow),BorderLayout.CENTER);
        menuWindow.getCont().repaint();
        menuWindow.setVisible(true);
    }*/


}
