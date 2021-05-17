package view.panels.buttons;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import model.SearchFlightsByDate;
import view.forms.flightForms.FlightForm;
import view.panels.menuBarPanels.optionsFlightPanels.AddFlightPanel;
import view.panels.menuBarPanels.optionsFlightPanels.ModifyFlightPanel;
import view.panels.menuBarPanels.searchPanels.DateFlightPanel;
import view.panels.menuBarPanels.searchPanels.SearchPilotPanel;
import view.panels.menuBarPanels.searchPanels.SeatReservationPanel;
import view.panels.menuWindowPanels.WelcomePanel;
import view.table.FlightBetween2DatesTable;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ButtonsPanel extends JPanel {
    private static MenuWindow menuWindow;
    private String typeAction;
    private ApplicationController controller = new ApplicationController();
    FlightForm flightForm;
    private JButton retour, validation, réinit;
    private GregorianCalendar start, end;

    public ButtonsPanel(MenuWindow menuWindow, String typeAction, FlightForm flightForm, String label){
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

    // recherche entre 2 dates
    public ButtonsPanel(MenuWindow menuWindow,String typeAction, GregorianCalendar start, GregorianCalendar end, String label){
        this(menuWindow, typeAction, null, label);
        this.start = start;
        this.end = end;
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
                    if(typeAction.equals("Addition")){
                        controller.addFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }else if(typeAction.equals("Modification")){
                        controller.modifyFlight(flight);
                        JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }else if(typeAction.equals("DateFlightSearch")){
                        ArrayList<SearchFlightsByDate> flights = controller.getAllFlightsBetweenDates(start, end);
                        FlightBetween2DatesTable flightTable = new FlightBetween2DatesTable(controller);

                    }
                }

                takeOut();
            } catch (FlightException.NumberFlightException | FlightException.MealDescriptionException | SQLException
                    | DataBaseConnectionException | DataBaseAccessException e) {
                e.printStackTrace();
            }
        }
    }

    // bouton de réinitialisation
    private class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt){
            try {
                takeOut();
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void takeOut() throws SQLException, DataBaseConnectionException {
        menuWindow.getCont().removeAll();

        if(typeAction.equals("Addition"))
            menuWindow.getCont().add(new AddFlightPanel(menuWindow), BorderLayout.CENTER);
        else if(typeAction.equals("Modification"))
            menuWindow.getCont().add(new ModifyFlightPanel(menuWindow), BorderLayout.CENTER);
        else if(typeAction.equals("DateFlightSearch"))
            menuWindow.getCont().add(new DateFlightPanel(menuWindow), BorderLayout.CENTER);
        else if(typeAction.equals("SearchPilot"))
            menuWindow.getCont().add(new SearchPilotPanel(menuWindow), BorderLayout.CENTER);
        else if(typeAction.equals("SeatReservationSearch"))
            menuWindow.getCont().add(new SeatReservationPanel(menuWindow), BorderLayout.CENTER);

        menuWindow.getCont().repaint();
        menuWindow.setVisible(true);
    }
}


