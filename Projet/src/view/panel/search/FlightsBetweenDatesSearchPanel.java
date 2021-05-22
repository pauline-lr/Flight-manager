package view.panel.search;

import controller.ApplicationController;
import exception.dataBase.DataBaseAccessException;
import exception.SearchDateException;
import model.search.FlightsBetweenDatesSearch;
import view.form.search.FlightsBetweenDatesSearchForm;
import view.panel.list.FlightsBetweenDatesListPanel;

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

    public FlightsBetweenDatesSearchPanel() throws DataBaseAccessException {
        setController(new ApplicationController());
        this.panel = this;
        this.flightsBetweenDatesSearchForm = new FlightsBetweenDatesSearchForm();
        this.setLayout(new BorderLayout());
        this.add(flightsBetweenDatesSearchForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);

        /*FlightsBetweenDatesSearchTable flightTable = new FlightsBetweenDatesSearchTable(controller, flights);
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
        GregorianCalendar startDate = flightsBetweenDatesSearchForm.getFirstDateCalendar();
        GregorianCalendar endDate = flightsBetweenDatesSearchForm.getLastDateCalendar();
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (startDate.compareTo(endDate) <= 0) {
                panel.removeAll();
                ArrayList<FlightsBetweenDatesSearch> flights = null;
                try {
                    flights = controller.getAllFlightsBetweenDates(flightsBetweenDatesSearchForm.getFirstDateCalendar(), flightsBetweenDatesSearchForm.getFirstDateCalendar());
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    panel.add(new FlightsBetweenDatesListPanel(flights));
                } catch (DataBaseAccessException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
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
