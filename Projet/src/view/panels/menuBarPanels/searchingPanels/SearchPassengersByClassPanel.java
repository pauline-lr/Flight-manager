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
    private ApplicationController controller;
    private SearchPassengersByClassPanel panel;
    private SearchPassengersByClassForm searchPassengersByClassForm;
    private JButton validation;

    public SearchPassengersByClassPanel(MenuWindow menuWindow) throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        this.panel = this;
        this.searchPassengersByClassForm = new SearchPassengersByClassForm();
        this.setLayout(new BorderLayout());
        this.add(searchPassengersByClassForm, BorderLayout.PAGE_START);

        this.validation = new JButton("Rechercher");
        validation.addActionListener(new ValidationListener());
        this.add(validation, BorderLayout.PAGE_END);

    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
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
