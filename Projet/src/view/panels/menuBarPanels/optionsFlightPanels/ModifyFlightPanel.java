package view.panels.menuBarPanels.optionsFlightPanels;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import view.forms.flightForms.FlightForm;
import view.forms.flightForms.ModifyFlightForm;
import view.panels.buttons.ButtonsPanel;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifyFlightPanel extends JPanel {
    private ApplicationController controller;
    private MenuWindow menuWindow;
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;

    public ModifyFlightPanel(MenuWindow menuWindow) {
        this.flightForm = new FlightForm();
        this.menuWindow = menuWindow;
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.LINE_START);
        this.add(new ButtonsPanel(menuWindow, "Modification", flightForm), BorderLayout.SOUTH);
    }

    // bouton de validation
  /*private class ValidationListener(Flight flightForm) implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            Flight flight = flightForm.getFlight();
            try {
                flight = flightForm.getFlight();
               // if(numéro de flight a été modifé){
                    controller.modifyFlight(flight, flight.getNumber());
                }else{
                    controller.modifyFlight(flight);
                }
                JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                takeOut();
            } catch (FlightException.NumberFlightException e) {
                e.printStackTrace();
            } catch (FlightException.MealDescriptionException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException e) {
                e.printStackTrace();
            }
        }
    }*/

    // bouton de réinitialisation
  /* private class RéinitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            takeOut();
        }
    }

    public void takeOut(){
        menuWindow.getCont().removeAll();
        menuWindow.getCont().add(new ModifyFlightPanel(menuWindow),BorderLayout.CENTER);
        menuWindow.getCont().repaint();
        menuWindow.setVisible(true);
    }*/

}

