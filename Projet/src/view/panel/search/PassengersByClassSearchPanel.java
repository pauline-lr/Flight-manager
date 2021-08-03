package view.panel.search;

import controller.ApplicationController;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import model.search.PassengersByClassSearch;
import view.CheckEmptyResult;
import view.form.search.PassengersByClassSearchForm;
import view.panel.list.PassengersByClassResultPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PassengersByClassSearchPanel extends JPanel {
    private ApplicationController controller;
    private PassengersByClassSearchPanel panel;
    private PassengersByClassSearchForm passengersByClassSearchForm;
    private JButton validation;

    public PassengersByClassSearchPanel() throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        this.panel = this;
        this.passengersByClassSearchForm = new PassengersByClassSearchForm();
        this.setLayout(new BorderLayout());
        this.add(passengersByClassSearchForm, BorderLayout.PAGE_START);

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
        @Override
        public void actionPerformed(ActionEvent evt) {
            panel.removeAll();
            ArrayList<PassengersByClassSearch> passengers = new ArrayList<>();
            String className = (String) passengersByClassSearchForm.getClassComboBox().getSelectedItem();

            try {
                passengers = controller.getAllPassengersOfAClass(className);
            } catch (AllDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            panel.add(new PassengersByClassResultPanel(passengers), BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
            CheckEmptyResult.checkResultIsEmpty(passengers, validation);
        }
    }
}
