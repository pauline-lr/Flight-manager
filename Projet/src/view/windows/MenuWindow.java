package view.windows;

import controller.ApplicationController;
import exception.DataBaseCloseException;
import exception.DataBaseConnectionException;
import view.panels.menuBarPanels.listingFlightsPanels.ListAllFlightsPanel;
import view.panels.menuBarPanels.editingFlightsPanels.AddFlightPanel;
import view.panels.menuBarPanels.editingFlightsPanels.DeleteFlightPanel;
import view.panels.menuBarPanels.editingFlightsPanels.ModifyFlightPanel;
import view.panels.menuBarPanels.searchingPanels.SearchFlightsBetweenDatesPanel;
import view.panels.menuBarPanels.searchingPanels.SearchFlightsByPilotPanel;
import view.panels.menuBarPanels.searchingPanels.SearchPassengersByClassPanel;
import view.panels.menuWindowPanels.AnimationSpace;
import view.panels.menuWindowPanels.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

// Attention close dans ExitListener et ExitButtonListener

public class MenuWindow extends JFrame{
    private ApplicationController controller;
    private Container frameContainer;
    private AnimationSpace animationSpace;
    private JMenuBar menuBar;
    private JMenu flight, listingFlight, search, application;
    private JMenuItem exit;
    private JMenuItem addFlight, modifyFlight, deleteFlight;
    private JMenuItem listingFlightItem;
    private JMenuItem dateFlight, seatReservation, searchPilot;

    public MenuWindow(){
        controller = new ApplicationController();
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
        this.animationSpace = new AnimationSpace();
        animationSpace.setLayout(new BorderLayout());
        animationSpace.setBounds(0,120,500,100);
        frameContainer.add(animationSpace);
    }

    public void initOptionsApplication(){
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
        deleteFlight.addActionListener(new DeletelightListener());
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

    public Container getCont() {
        return frameContainer;
    }

    private class ExitButtonListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent evt){
            ApplicationController controller = new ApplicationController();
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
                frameContainer.add(new AddFlightPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
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
                frameContainer.add(new ModifyFlightPanel(MenuWindow.this, controller), BorderLayout.CENTER);
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
            frameContainer.add(new SearchFlightsBetweenDatesPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SeatReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new SearchPassengersByClassPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SearchPilotFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            try {
                frameContainer.add(new SearchFlightsByPilotPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            } catch (SQLException | DataBaseConnectionException throwables) {
                throwables.printStackTrace();
            }
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ListingFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new ListAllFlightsPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class DeletelightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new DeleteFlightPanel(MenuWindow.this, controller), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }
}
