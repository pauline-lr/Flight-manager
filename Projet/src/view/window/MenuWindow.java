package view.window;

import controller.ApplicationController;
import exception.dataBase.*;
import exception.*;
import view.panel.list.*;
import view.panel.edit.*;
import view.panel.search.*;
import view.panel.home.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MenuWindow extends JFrame{
    private ApplicationController controller;
    private Container frameContainer;
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
        setMinimumSize(new Dimension(450, 500));
        frameContainer = this.getContentPane();
        this.addWindowListener(new ExitButtonListener());

        initMenuBar();

        initAnimation();
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
        frameContainer.add(new WelcomePanel(), BorderLayout.PAGE_START);
        frameContainer.add(new AnimationPanel(), BorderLayout.CENTER);
        MenuWindow.this.getContainer().repaint();
        MenuWindow.this.setVisible(true);
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
            closeConnection();
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            controller.closeConnection();
        } catch (DataBaseCloseException exception) {
            exception.printStackTrace();
        }
        System.exit(0);
    }

    private class AddFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new AddFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
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
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.MealDescriptionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException throwables) {
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
                frameContainer.add(new DeleteFlightPanel(), BorderLayout.CENTER);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.MealDescriptionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage( ),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class HomeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            MenuWindow.this.getContainer().removeAll();
            frameContainer.add(new WelcomePanel(), BorderLayout.PAGE_START);
            frameContainer.add(new AnimationPanel(), BorderLayout.CENTER);
            MenuWindow.this.getContainer().repaint();
            MenuWindow.this.setVisible(true);
        }
    }
}
