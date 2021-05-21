package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import exception.DataBaseConnectionException;
import model.SearchFlightsByPilot;
import tool.Format;
import tool.GetID;
import view.forms.searchForms.SearchFlightsByPilotForm;
import view.resultSearch.ResultSearchFlightsByPilot;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchFlightsByPilotPanel extends JPanel {
    private SearchFlightsByPilotPanel panel;
    private ApplicationController controller;
    private SearchFlightsByPilotForm searchFlightsByPilotForm;
    private JButton validation;

    public SearchFlightsByPilotPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.panel = this;
        this.controller = new ApplicationController();
        this.searchFlightsByPilotForm = new SearchFlightsByPilotForm();
        this.setLayout(new BorderLayout());
        this.add(searchFlightsByPilotForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);
    }

    private class ValidationListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            panel.removeAll();
            ArrayList<SearchFlightsByPilot> flights;
            try {
                flights = controller.getAllFlightsOfAPilot(GetID.getPilotId(searchFlightsByPilotForm.getPilotComboBox()));
                panel.add(new ResultSearchFlightsByPilot(flights), BorderLayout.CENTER);
                panel.repaint();
            } catch (DataBaseAccessException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
            }


        }
    }
}
