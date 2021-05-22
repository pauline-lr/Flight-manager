package view.panel.button;

import controller.ApplicationController;
import exception.dataBase.DataBaseAccessException;
import exception.dataBase.DataBaseConnectionException;
import exception.FlightException;
import model.Flight;
import model.search.FlightsByPilotSearch;
import view.form.edit.FlightForm;
import view.panel.edit.AddFlightPanel;
import view.panel.edit.ModifyFlightPanel;
import view.panel.home.WelcomePanel;
import view.panel.search.FlightsBetweenDatesSearchPanel;
import view.panel.search.FlightsByPilotSearchPanel;
import view.panel.search.PassengersByClassSearchPanel;
import view.table.search.FlightsByPilotSearchTable;
import view.window.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ButtonsPanel extends JPanel {
    private ApplicationController controller;
    private static MenuWindow menuWindow;
    private String typeAction;
    private FlightForm flightForm;
    private JButton retour, validation, réinit;
    private GregorianCalendar start, end;
    private FlightsBetweenDatesSearchPanel dateFlight;
    private FlightsByPilotSearchPanel flightByPilotPanel;
    private String pilotId;
    private PassengersByClassSearchPanel classByPassengerPanel;
    private String classId;
    private JTable table;
    private ListSelectionModel listSelect;

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

    // recherche entre 2 dates
    /*public ButtonsPanel(MenuWindow menuWindow, String typeAction, FlightsBetweenDatesSearchPanel dateFlight,
                        GregorianCalendar start, GregorianCalendar end, String label, ApplicationController controller){
        this(menuWindow, typeAction, null, label, controller);
        this.dateFlight = dateFlight;
        this.start = start;
        this.end = end;
    }*/

    // recherche  pilote
    /*public ButtonsPanel(MenuWindow menuWindow, String typeAction, FlightsByPilotSearchPanel flightByPilotPanel,
                        String pilotId, String label, ApplicationController controller){
        this(menuWindow, typeAction, null, label, controller);
        this.flightByPilotPanel = flightByPilotPanel;
        this.pilotId = pilotId;
    }*/

    // recherche  passenger
    /*public ButtonsPanel(MenuWindow menuWindow, String typeAction, PassengersByClassSearchPanel classByPassengerPanel,
                        String classId, String label, ApplicationController controller){
        this(menuWindow, typeAction, null, label, controller);
        this.classByPassengerPanel = classByPassengerPanel;
        this.classId = classId;
    }*/

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
                        switch (typeAction) {
                            case "Modification" -> {
                                //Flight flight = modifyFlight.getFlight()
                                //controller.modifyFlight(flight);
                                JOptionPane.showMessageDialog(null, "Vol modifié", "Succès", JOptionPane.INFORMATION_MESSAGE);
                            }
                            case "DateFlightSearch" -> {
                               /* if (start.compareTo(end) < 0) {
                                    FlightsBetweenDatesSearchTable flightTable;
                                    RowSorter<FlightsBetweenDatesSearchTable> sorter;
                                    TableColumn column;
                                    JScrollPane scrollPane;
                                    ArrayList<FlightsBetweenDatesSearch> flights = controller.getAllFlightsBetweenDates(start, end);
                                    /*FlightsBetweenDatesSearchTable flightTable = new FlightsBetweenDatesSearchTable(controller, flights);
                                    JTable table = new JTable(flightTable);
                                    table.setModel(flightTable);
                                    dateFlight.add(new JScrollPane(table), BorderLayout.CENTER);
                                    //

                                    if(flights == null){
                                        flightTable = new FlightsBetweenDatesSearchTable(controller);
                                    }else{
                                        flightTable = new FlightsBetweenDatesSearchTable(controller, flights);
                                    }

                                    table = new JTable(flightTable);
                                    sorter = new TableRowSorter<>(flightTable);
                                    table.setRowSorter(sorter);
                                    column = table.getColumnModel().getColumn(1);
                                    column.setPreferredWidth(300);

                                    scrollPane = new JScrollPane(table);
                                    listSelect = table.getSelectionModel();
                                    dateFlight.add(scrollPane, BorderLayout.CENTER);
                                   // dateFlight.add(new ResultSearchFlightBetweenDates(controller, flights), BorderLayout.CENTER);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Veuillez entrer une première date antérieure à l'autre ", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }*/

                            }
                            case "SearchPilot" -> {
                            /*ArrayList<FlightsByPilotSearch> flights = controller.getAllFlightsOfAPilot(pilotId);
                            FlightsByPilotSearchTable flightTable = new FlightsByPilotSearchTable(controller, flights);
                            JTable table = new JTable(flightTable);
                            table.setModel(flightTable);
                            flightByPilotPanel.add(new JScrollPane(table), BorderLayout.CENTER);*/
                                ArrayList<FlightsByPilotSearch> flights = controller.getAllFlightsOfAPilot(pilotId);
                                FlightsByPilotSearchTable flightTable = new FlightsByPilotSearchTable(flights);
                                JTable table = new JTable(flightTable);
                                flightByPilotPanel.add(new JScrollPane(table), BorderLayout.CENTER);
                            }
                            case "SeatReservationSearch" -> {

                            }
                        }
                    }

                } catch (SQLException | FlightException.NumberFlightException | FlightException.MealDescriptionException |
                    DataBaseAccessException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
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


