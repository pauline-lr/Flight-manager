package view.windows;

import view.panels.menuBarPanels.listingPanels.AllFlightsPanel;
import view.panels.menuBarPanels.optionsFlightPanels.AddFlightPanel;
import view.panels.menuBarPanels.optionsFlightPanels.DeleteFlightPanel;
import view.panels.menuBarPanels.optionsFlightPanels.ModifyFlightPanel;
import view.panels.menuBarPanels.searchPanels.DateFlightPanel;
import view.panels.menuBarPanels.searchPanels.SearchPilotPanel;
import view.panels.menuBarPanels.searchPanels.SeatReservationPanel;
import view.panels.menuWindowPanels.AnimationSpace;
import view.panels.menuWindowPanels.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MenuWindow extends JFrame{
    private Container frameContainer;
    private AnimationSpace animationSpace;
    private JMenuBar menuBar;
    private JMenu flight, listingFlight, search, application;
    private JMenuItem exit;
    private JMenuItem addFlight, modifyFlight, deleteFlight;
    private JMenuItem listingFlightItem;
    private JMenuItem dateFlight, seatReservation, searchPilot;

    public MenuWindow(){
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
            System.exit(0);
        }
    }

    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            System.exit(0);
        }
    }

    private class AddFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new AddFlightPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ModifyFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new ModifyFlightPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class DateFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new DateFlightPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SeatReservationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new SeatReservationPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class SearchPilotFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new SearchPilotPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class ListingFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new AllFlightsPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }

    private class DeletelightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt){
            frameContainer.removeAll();
            frameContainer.add(new DeleteFlightPanel(MenuWindow.this), BorderLayout.CENTER);
            frameContainer.repaint();
            MenuWindow.this.setVisible(true);
        }
    }


}
