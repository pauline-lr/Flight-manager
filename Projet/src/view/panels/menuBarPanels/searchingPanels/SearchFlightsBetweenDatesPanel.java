package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import model.SearchFlightsBetweenDates;
import view.forms.searchForms.SearchFlightsBetweenDatesForm;
import view.resultSearch.ResultSearchFlightBetweenDates;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchFlightsBetweenDatesPanel extends JPanel {
    private SearchFlightsBetweenDatesPanel panel;
    private ApplicationController controller;
    private SearchFlightsBetweenDatesForm searchFlightsBetweenDatesForm;
    private JButton validation;

    public SearchFlightsBetweenDatesPanel(ApplicationController controller) throws DataBaseAccessException {
        this.panel = this;
        this.controller = controller;
        this.searchFlightsBetweenDatesForm = new SearchFlightsBetweenDatesForm();
        this.setLayout(new BorderLayout());
        this.add(searchFlightsBetweenDatesForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);

        /*SearchFlightsBetweenDatesTable flightTable = new SearchFlightsBetweenDatesTable(controller, flights);
        JTable table = new JTable(flightTable);
        table.setModel(flightTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);*/

        /*this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this,
                searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getFirstDateCalendar(), "Rechercher", controller), BorderLayout.SOUTH);*/
    }

    private class ValidationListener implements ActionListener {
        GregorianCalendar startDate = searchFlightsBetweenDatesForm.getFirstDateCalendar();
        GregorianCalendar endDate = searchFlightsBetweenDatesForm.getLastDateCalendar();
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (startDate.compareTo(endDate) < 0) {
                panel.removeAll();
                ArrayList<SearchFlightsBetweenDates> flights = null;
                try {
                    flights = controller.getAllFlightsBetweenDates(searchFlightsBetweenDatesForm.getFirstDateCalendar(), searchFlightsBetweenDatesForm.getFirstDateCalendar());
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Type d'erreur : " +  e.getMessage() +
                            "\n Merci de contacter le service informatique, Nous nous excusons pour le désagrément", "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    panel.add(new ResultSearchFlightBetweenDates(flights));
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Type d'erreur : " +  e.getMessage() +
                            "\n Merci de contacter le service informatique, Nous nous excusons pour le désagrément", "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
                }
                panel.repaint();
            } else {
                // exception
                JOptionPane.showMessageDialog(null, "Veuillez entrer une première date antérieure à l'autre ", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
