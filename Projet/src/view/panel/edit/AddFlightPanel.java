package view.panel.edit;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import model.Flight;
import view.form.edit.FlightForm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class AddFlightPanel extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;
    private JButton validate;

    public AddFlightPanel() throws SQLException, DataBaseConnectionException {
        this.flightForm = new FlightForm();
        this.controller = new ApplicationController();
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);

        validate = new JButton("Ajouter");
        validate.addActionListener(new ValidationListener());
        buttonPanel.add(validate, BorderLayout.CENTER);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                Flight flight = flightForm.getFlight();
                controller.addFlight(flight);
                JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (DataBaseConnectionException throwables) {
                throwables.printStackTrace();
                JOptionPane.showMessageDialog(null, throwables.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
