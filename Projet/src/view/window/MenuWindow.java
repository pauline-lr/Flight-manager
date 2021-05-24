package view.window;

import controller.ApplicationController;
import exception.dataBase.DataBaseCloseException;
import exception.dataBase.DataBaseConnectionException;
import exception.FlightException;
import view.panel.list.AllFlightsListPanel;
import view.panel.edit.AddFlightPanel;
import view.panel.edit.DeleteFlightPanel;
import view.panel.edit.ModifyFlightPanel;
import view.panel.search.FlightsBetweenDatesSearchPanel;
import view.panel.search.FlightsByPilotSearchPanel;
import view.panel.search.PassengersByClassSearchPanel;
import view.panel.home.AnimationPanel;
import view.panel.home.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MenuWindow extends JFrame{
    private ApplicationController controller;
    private Container frameContainer;
    private AnimationPanel animationPanel;
    private JMenuBar menuBar;
    private JMenu flight, listingFlight, search, application;
    private JMenuItem home, exit;
    private JMenuItem addFlight, modifyFlight, deleteFlight;
    private JMenuItem listingFlightItem;
    private JMenuItem dateFlight, seatReservation, searchPilot;

    public MenuWindow(){
        setController(new ApplicationController());
        setTitle("Gestionnaire de vols");
        setBounds(100,100,600,600);
        frameContainer = this.getContentPane();
        this.addWindowListener(new ExitButtonListener());

        initMenuBar();

        initAnimation();

        BorderLayout border = new BorderLayout();
        this.setLayout(border);
        frameContainer.add(new WelcomePanel(), border.PAGE_START);
        this.setVisible(true);
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void initMenuBar(){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        flight = new JMenu("Editer");
        listingFlight = new JMenu("Lister");
        search = new JMenu("Rechercher");
        application = new JMenu("Application");

        menuBar.add(application);
        menuBar.add(flight);
        menuBar.add(listingFlight);
        menuBar.add(search);

        initOptionsApplication();

        initOptionsFlight();

        initListing();

        initOptionsSearch();
    }

    public void initAnimation(){
        this.animationPanel = new AnimationPanel();
        animationPanel.setLayout(new BorderLayout());
        frameContainer.add(animationPanel, BorderLayout.CENTER);
        animationPanel.setBounds(0,120,this.getWidth(), 100);
    }

    public void initOptionsApplication(){
        home = new JMenuItem("Accueil");
        home.addActionListener(new HomeListener());
        application.add(home);

        exit = new JMenuItem("Quitter");
        exit.addActionListener(new ExitListener());
        application.add(exit);
    }

    public void initOptionsFlight(){
        addFlight = new JMenuItem("Ajouter un vol");
        addFlight.addActionListener(new AddFlightListener());
        flight.add(addFlight);

        modifyFlight = new JMenuItem("Modifier un vol");
        modifyFlight.addActionListener(new ModifyFlightListener());
        flight.add(modifyFlight);

        deleteFlight = new JMenuItem("Supprimer un vol");
        deleteFlight.addActionListener(new DeleteFlightListener());
        flight.add(deleteFlight);
    }

    public void initListing(){
        listingFlightItem = new JMenuItem("Vols à venir");
        listingFlightItem.addActionListener(new ListingFlightListener());
        listingFlight.add(listingFlightItem);
    }

    public void initOptionsSearch(){
        dateFlight = new JMenuItem("Vols entre deux dates");
        dateFlight.addActionListener(new DateFlightListener());
        search.add(dateFlight);

        seatReservation = new JMenuItem("Passagers d'une classe");
        seatReservation.addActionListener(new SeatReservationListener());
        search.add(seatReservation);

        searchPilot = new JMenuItem("Vols d'un pilote");
        searchPilot.addActionListener(new SearchPilotFlightListener());
        search.add(searchPilot);
    }

    public Container getContainer() {
        return frameContainer;
    }

    private class ExitButtonListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent evt){
            try {
                controller.closeConnection();
            } catch (DataBaseCloseException exception) {
                exception.printStackTrace();
            }
            System.exit(0);
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            try {
                controller.closeConnection();
            } catch (DataBaseCloseException exception) {
                exception.printStackTrace();
            }
            System.exit(0);
        }
    }

    private class AddFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new AddFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ModifyFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new ModifyFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class DateFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new FlightsBetweenDatesSearchPanel(), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SeatReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new PassengersByClassSearchPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SearchPilotFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new FlightsByPilotSearchPanel(), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ListingFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new AllFlightsListPanel(), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException | FlightException.MealDescriptionException | FlightException.NumberFlightException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class DeleteFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new DeleteFlightPanel(MenuWindow.this), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException | FlightException.MealDescriptionException
                    | FlightException.NumberFlightException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class HomeListener implements ActionListener {
        /*code du boutton retour */
        @Override
        public void actionPerformed(ActionEvent evt) {
            MenuWindow.this.getContainer().removeAll();
           //MenuWindow.this.getContainer().add(new MenuWindow(), BorderLayout.CENTER);
            frameContainer.add(new WelcomePanel(), BorderLayout.PAGE_START);
            frameContainer.add(new AnimationPanel(), BorderLayout.CENTER);
            MenuWindow.this.getContainer().repaint();
            MenuWindow.this.setVisible(true);
        }
    }
}