package view.panels.menuBarPanels.searchingPanels;

import controller.ApplicationController;
import exception.DataBaseAccessException;
import exception.DataBaseConnectionException;
import model.SearchFlightsBetweenDates;
import model.SearchPassengersByClass;
import tool.GetID;
import view.forms.searchForms.SearchPassengersByClassForm;
import view.panels.buttons.ButtonsPanel;
import view.resultSearch.ResultSearchFlightsBetweenDates;
import view.resultSearch.ResultSearchPassengersByClass;
import view.windows.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchPassengersByClassPanel extends JPanel {
    private SearchPassengersByClassPanel panel;
    private ApplicationController controller;
    private SearchPassengersByClassForm searchPassengersByClassForm;
    private JButton validation;

    public SearchPassengersByClassPanel(MenuWindow menuWindow, ApplicationController controller) throws SQLException, DataBaseConnectionException {
        this.panel = this;
        this.controller = new ApplicationController();
        this.searchPassengersByClassForm = new SearchPassengersByClassForm();
        this.setLayout(new BorderLayout());
        this.add(searchPassengersByClassForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);

    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            panel.removeAll();
            ArrayList<SearchPassengersByClass> flights;
            try {
                flights = controller.getAllPassengersOfAClass(GetID.getClassId(searchPassengersByClassForm.getClassComboBox()));
                panel.add(new ResultSearchPassengersByClass(flights), BorderLayout.CENTER);
                panel.repaint();
            } catch (DataBaseAccessException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
