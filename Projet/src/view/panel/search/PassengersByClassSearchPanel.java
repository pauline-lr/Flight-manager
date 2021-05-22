package view.panel.search;

import controller.ApplicationController;
import exception.dataBase.DataBaseAccessException;
import exception.dataBase.DataBaseConnectionException;
import model.search.PassengersByClassSearch;
import tool.GetID;
import view.form.search.PassengersByClassSearchForm;
import view.panel.list.PassengersByClassResultPanel;
import view.window.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassengersByClassSearchPanel extends JPanel {
    private ApplicationController controller;
    private PassengersByClassSearchPanel panel;
    private PassengersByClassSearchForm passengersByClassSearchForm;
    private JButton validation;

    public PassengersByClassSearchPanel(MenuWindow menuWindow) throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        this.panel = this;
        this.passengersByClassSearchForm = new PassengersByClassSearchForm();
        this.setLayout(new BorderLayout());
        this.add(passengersByClassSearchForm, BorderLayout.PAGE_START);

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
            ArrayList<PassengersByClassSearch> flights;
            try {
                flights = controller.getAllPassengersOfAClass(GetID.getClassId(passengersByClassSearchForm.getClassComboBox()));
                panel.add(new PassengersByClassResultPanel(flights), BorderLayout.CENTER);
                panel.repaint();
            } catch (DataBaseAccessException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,  e.getMessage(), "Erreur liée à la BD", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
