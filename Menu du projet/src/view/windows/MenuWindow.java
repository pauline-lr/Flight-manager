package view.windows;

import view.panels.AddFlightPanel;
import view.panels.WelcomePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MenuWindow extends JFrame{
    private Container frameContainer;
    private MenuWindow panel;
    private JMenuBar menuBar;
    private JMenu flight, linstingFlight, search, application;
    private JMenuItem exit;
    private JMenuItem addFlight, modifyFlight, deleteFlight;
    private JMenuItem dateFlight, listingPassenger, searchPilot;

    public MenuWindow(){
        setTitle("Gestionnaire de vols");
        setBounds(100,100,500,500);
        frameContainer = this.getContentPane();
        this.addWindowListener(new ExitButtonListener());


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        flight = new JMenu("Vols");
        linstingFlight = new JMenu("Lister vols");
        search = new JMenu("Recherche...");
        application = new JMenu("Application");

        menuBar.add(application);
        menuBar.add(flight);
        menuBar.add(linstingFlight);
        menuBar.add(search);

        exit = new JMenuItem("Quitter");
        exit.addActionListener(new ExitListener());
        application.add(exit);
        // Items for flight
        addFlight = new JMenuItem("Ajouter un vol");
        addFlight.addActionListener(new AddFlightListener());
        flight.add(addFlight);

        modifyFlight = new JMenuItem("Modifier un vol");
        //modifyFlight.addActionListener(new ModifyFlightListener());
        flight.add(modifyFlight);

        deleteFlight = new JMenuItem("Supprimer un vol");
        //deleteFlight.addActionListener(new DeletelightListener());
        flight.add(deleteFlight);

        // Items for search
        dateFlight = new JMenuItem("de vols par date");
        //dateFlight.addActionListener(new DateFlightListener());
        search.add(dateFlight);

        listingPassenger = new JMenuItem("de passagers");
        //listingPassenger.addActionListener(new ListingPassengerListener());
        search.add(listingPassenger);

        searchPilot = new JMenuItem("de pilotes");
        //searchPilot.addActionListener(new SearchPilotFlightListener());
        search.add(searchPilot);


        this.setLayout(new BorderLayout());
        frameContainer.add(new WelcomePanel(), BorderLayout.CENTER);
        this.setVisible(true);
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
/*
    private class ModifyFlightListener implements ActionListener {
    }

    private class DeletelightListener implements ActionListener {
    }*/
}
