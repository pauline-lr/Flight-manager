package view.panel.search;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import model.search.FlightsByPilotSearch;
import tool.GetID;
import view.CheckEmptyResult;
import view.form.search.FlightsByPilotSearchForm;
import view.panel.list.FlightsByPilotResultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightsByPilotSearchPanel extends JPanel {
    private ApplicationController controller;
    private FlightsByPilotSearchPanel panel;
    private FlightsByPilotSearchForm flightsByPilotSearchForm;
    private JButton validation;

    public FlightsByPilotSearchPanel()  {
        setController(new ApplicationController());
        this.panel = this;
        try {
            this.flightsByPilotSearchForm = new FlightsByPilotSearchForm();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (DataBaseConnectionException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage( ),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        this.setLayout(new BorderLayout());
        this.add(flightsByPilotSearchForm, BorderLayout.PAGE_START);

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
            panel.removeAll();
            ArrayList<FlightsByPilotSearch> flights;
            JComboBox pilot = flightsByPilotSearchForm.getPilotComboBox();
            try {
                flights = controller.getAllFlightsOfAPilot(GetID.getPilotID(pilot));
                panel.add(new FlightsByPilotResultPanel(flights), BorderLayout.CENTER);
                panel.revalidate();
                panel.repaint();
                CheckEmptyResult.checkResultIsEmpty(flights);
            } catch (DataBaseAccessException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}
