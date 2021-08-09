package view.window;

import controller.ApplicationController;
import exception.FlightException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseCloseException;
import exception.dataBase.DataBaseConnectionException;
import view.CheckEmptyResult;
import view.panel.edit.AddFlightPanel;
import view.panel.edit.DeleteFlightPanel;
import view.panel.edit.ModifyFlightPanel;
import view.panel.home.AnimationPanel;
import view.panel.home.WelcomePanel;
import view.panel.list.AllFlightsListPanel;
import view.panel.search.FlightsBetweenDatesSearchPanel;
import view.panel.search.FlightsByPilotSearchPanel;
import view.panel.search.PassengersByClassSearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuWindow extends JFrame {
    private ApplicationController controller;
    private Container frameContainer;
    private JMenu flight, listingFlight, search, application;

    public MenuWindow() {
        setController(new ApplicationController());
        setTitle("Gestionnaire de vols");
        setBounds(100, 100, 600, 600);
        setMinimumSize(new Dimension(650, 650));
        frameContainer = this.getContentPane();
        this.addWindowListener(new ExitButtonListener());

        initMenuBar();

        initAnimation();
    }

    public void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
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

    public void initAnimation() {
        frameContainer.add(new WelcomePanel(), BorderLayout.PAGE_START);
        frameContainer.add(new AnimationPanel(), BorderLayout.CENTER);
        MenuWindow.this.getContainer().repaint();
        MenuWindow.this.setVisible(true);
    }

    public void initOptionsApplication() {
        JMenuItem home = new JMenuItem("Accueil");
        home.addActionListener(new HomeListener());
        application.add(home);

        JMenuItem exit = new JMenuItem("Quitter");
        exit.addActionListener(new ExitListener());
        application.add(exit);
    }

    public void initOptionsFlight() {
        JMenuItem addFlight = new JMenuItem("Ajouter un vol");
        addFlight.addActionListener(new AddFlightListener());
        flight.add(addFlight);

        JMenuItem modifyFlight = new JMenuItem("Modifier un vol");
        modifyFlight.addActionListener(new ModifyFlightListener());
        flight.add(modifyFlight);

        JMenuItem deleteFlight = new JMenuItem("Supprimer un vol");
        deleteFlight.addActionListener(new DeleteFlightListener());
        flight.add(deleteFlight);
    }

    public void initListing() {
        JMenuItem listingFlightItem = new JMenuItem("Vols à venir");
        listingFlightItem.addActionListener(new ListingFlightListener());
        listingFlight.add(listingFlightItem);
    }

    public void initOptionsSearch() {
        JMenuItem dateFlight = new JMenuItem("Vols entre deux dates");
        dateFlight.addActionListener(new DateFlightListener());
        search.add(dateFlight);

        JMenuItem seatReservation = new JMenuItem("Passagers d'une classe");
        seatReservation.addActionListener(new SeatReservationListener());
        search.add(seatReservation);

        JMenuItem searchPilot = new JMenuItem("Vols d'un pilote");
        searchPilot.addActionListener(new SearchPilotFlightListener());
        search.add(searchPilot);
    }

    public Container getContainer() {
        return frameContainer;
    }

    private class ExitButtonListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent evt) {
            try {
                closeConnection();
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                closeConnection();
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void closeConnection() throws DataBaseConnectionException {
        try {
            controller.closeConnection();
        } catch (DataBaseCloseException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    private class AddFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new AddFlightPanel(), BorderLayout.CENTER);
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ModifyFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            try {
                ModifyFlightPanel modifyFlightPanel = new ModifyFlightPanel();
                frameContainer.add(modifyFlightPanel, BorderLayout.CENTER);
                frameContainer.repaint();
                MenuWindow.this.setVisible(true);
                CheckEmptyResult.checkResultIsEmpty(controller.getAllFlightsToString(), modifyFlightPanel.getValidationButton());
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class DateFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            frameContainer.add(new FlightsBetweenDatesSearchPanel(), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SeatReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            try {
                frameContainer.add(new PassengersByClassSearchPanel(), BorderLayout.CENTER);
                frameContainer.repaint();
                MenuWindow.this.setVisible(true);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class SearchPilotFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            frameContainer.add(new FlightsByPilotSearchPanel(), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ListingFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();

            AllFlightsListPanel allFlightsListPanel = null;
            try {
                allFlightsListPanel = new AllFlightsListPanel();
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            assert allFlightsListPanel != null;
            frameContainer.add(allFlightsListPanel, BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
            CheckEmptyResult.checkResultIsEmpty(allFlightsListPanel.getFlightsTable().getFlights(), null);
        }
    }

    private class DeleteFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            frameContainer.removeAll();
            try {
                DeleteFlightPanel deleteFlightPanel = new DeleteFlightPanel();
                frameContainer.add(deleteFlightPanel, BorderLayout.CENTER);
                frameContainer.repaint();
                MenuWindow.this.setVisible(true);
                CheckEmptyResult.checkResultIsEmpty(deleteFlightPanel.getFlightsTable().getFlights(), deleteFlightPanel.getValidateDeletionButton());
            } catch (DataBaseConnectionException throwables) {
                JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
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
