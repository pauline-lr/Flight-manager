package view.panels.buttons;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import exception.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import model.SearchFlightsBetweenDates;
import model.SearchFlightsByPilot;
import view.forms.flightForms.AddFlightForm;
import view.panels.menuBarPanels.editingFlightsPanels.AddFlightPanel;
import view.panels.menuBarPanels.editingFlightsPanels.ModifyFlightPanel;
import view.panels.menuBarPanels.searchingPanels.SearchFlightsBetweenDatesPanel;
import view.panels.menuBarPanels.searchingPanels.SearchFlightsByPilotPanel;
import view.panels.menuBarPanels.searchingPanels.SearchPassengersByClassPanel;
import view.panels.menuWindowPanels.WelcomePanel;
import view.tables.SearchFlightsBetweenDatesTable;
import view.tables.SearchFlightsByPilotTable;
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
    private ApplicationController controller;
    private AddFlightForm addFlightForm;
    private JButton retour, validation, réinit;
    private GregorianCalendar start, end;
    private SearchFlightsBetweenDatesPanel dateFlight;
    private SearchFlightsByPilotPanel flightByPilotPanel;
    private String pilotId;
    private BorderLayout border;


    public ButtonsPanel(MenuWindow menuWindow, String typeAction, AddFlightForm addFlightForm, String label, ApplicationController controller){
        this.controller = controller;
        this.menuWindow = menuWindow;
        this.typeAction = typeAction;
        this.addFlightForm = addFlightForm;
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
    public ButtonsPanel(MenuWindow menuWindow, String typeAction, SearchFlightsBetweenDatesPanel dateFlight,
                        GregorianCalendar start, GregorianCalendar end, String label, ApplicationController controller, BorderLayout border){
        this(menuWindow, typeAction, null, label, controller);
        this.border = border;
        this.dateFlight = dateFlight;
        this.start = start;
        this.end = end;
    }

    // recherche  pilote
    public ButtonsPanel(MenuWindow menuWindow, String typeAction, SearchFlightsByPilotPanel flightByPilotPanel,
                        String pilotId, String label, ApplicationController controller){
        this(menuWindow, typeAction, null, label, controller);
        this.flightByPilotPanel = flightByPilotPanel;
        this.pilotId = pilotId;
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
                if(addFlightForm != null) {
                    Flight flight = addFlightForm.getFlight();
                    switch (typeAction) {
                        case "Addition" -> {
                            controller.addFlight(flight);
                            JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }
                        case "Modification" -> {
                            controller.modifyFlight(flight);
                            JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                        }
                        case "DateFlightSearch" -> {
                            if(start.compareTo(end) <= 0){
                                ArrayList<SearchFlightsBetweenDates> flights = controller.getAllFlightsBetweenDates(start, end);
                                SearchFlightsBetweenDatesTable flightTable = new SearchFlightsBetweenDatesTable(controller, flights);
                                JTable table = new JTable(flightTable);
                                table.setModel(flightTable);
                                dateFlight.add(new JScrollPane(table), border.CENTER);
                            }else{
                                JOptionPane.showMessageDialog(null, "Veuillez entrer une première date antérieure à l'autre ", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }

                        } case "SearchPilot" -> {
                            ArrayList<SearchFlightsByPilot> flights = controller.getAllFlightsOfAPilot(pilotId);
                            SearchFlightsByPilotTable flightTable = new SearchFlightsByPilotTable(controller, flights);
                            JTable table = new JTable(flightTable);
                            table.setModel(flightTable);
                            flightByPilotPanel.add(new JScrollPane(table), BorderLayout.CENTER);
                        }
                    }
                }

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

        if (typeAction.equals("Addition")) {
            menuWindow.getCont().add(new AddFlightPanel(menuWindow, controller), BorderLayout.CENTER);
        } else if (typeAction.equals("Modification")) {
            menuWindow.getCont().add(new ModifyFlightPanel(menuWindow, controller), BorderLayout.CENTER);
        } else if (typeAction.equals("DateFlightSearch")) {
            menuWindow.getCont().add(new SearchFlightsBetweenDatesPanel(menuWindow, controller), BorderLayout.CENTER);
        } else if(typeAction.equals("SearchPilot")) {
            menuWindow.getCont().add(new SearchFlightsByPilotPanel(menuWindow, controller), BorderLayout.CENTER);
        } else if(typeAction.equals("SeatReservationSearch")) {
            menuWindow.getCont().add(new SearchPassengersByClassPanel(menuWindow, controller), BorderLayout.CENTER);
        }

        menuWindow.getCont().repaint();
        menuWindow.setVisible(true);
    }
}


