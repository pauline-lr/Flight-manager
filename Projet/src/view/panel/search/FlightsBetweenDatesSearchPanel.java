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

        JPanel buttonPanel = new JPanel();
        validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(validation, BorderLayout.CENTER);

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
                ArrayList<FlightsBetweenDatesSearch> flights;
                try {
                    flights = controller.getAllFlightsBetweenDates(startDate, endDate);
                    panel.add(new FlightsBetweenDatesResultPanel(flights), BorderLayout.CENTER);
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la base de données", JOptionPane.ERROR_MESSAGE);
                }
                panel.revalidate();
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
