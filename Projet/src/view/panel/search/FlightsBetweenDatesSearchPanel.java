package view.panel.search;

import controller.ApplicationController;
import exception.dataBase.DataBaseAccessException;
import exception.SearchDateException;
import model.search.FlightsBetweenDatesSearch;
import view.form.search.FlightsBetweenDatesSearchForm;
import view.panel.list.FlightsBetweenDatesResultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightsBetweenDatesSearchPanel extends JPanel {
    private ApplicationController controller;
    private FlightsBetweenDatesSearchPanel panel;
    private FlightsBetweenDatesSearchForm flightsBetweenDatesSearchForm;
    private JButton validation;

    public FlightsBetweenDatesSearchPanel() {
        setController(new ApplicationController());
        this.panel = this;
        this.flightsBetweenDatesSearchForm = new FlightsBetweenDatesSearchForm();
        this.setLayout(new BorderLayout());
        this.add(flightsBetweenDatesSearchForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);

        /*FlightsBetweenDatesResultTable flightTable = new FlightsBetweenDatesResultTable(controller, flights);
        JTable table = new JTable(flightTable);
        table.setModel(flightTable);
        this.add(new JScrollPane(table), BorderLayout.CENTER);*/

        /*this.add(new ButtonsPanel(menuWindow, "DateFlightSearch", this,
                flightsBetweenDatesSearchForm.getFirstDateCalendar(), flightsBetweenDatesSearchForm.getFirstDateCalendar(), "Rechercher", controller), BorderLayout.SOUTH);*/
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private class ValidationListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            GregorianCalendar startDate = flightsBetweenDatesSearchForm.getStartDate();
            GregorianCalendar endDate = flightsBetweenDatesSearchForm.getEndDate();
            if (startDate.compareTo(endDate) <= 0) {
                panel.removeAll();
                ArrayList<FlightsBetweenDatesSearch> flights = null;
                try {
                    flights = controller.getAllFlightsBetweenDates(startDate, endDate);
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la base de données", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    panel.add(new FlightsBetweenDatesResultPanel(flights), BorderLayout.CENTER);
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la base de données", JOptionPane.ERROR_MESSAGE);
                }
                panel.repaint();
            } else {
                try {
                    throw new SearchDateException();
                } catch (SearchDateException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage() , "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
