package view.panel.edit;

import controller.ApplicationController;
import exception.dataBase.DataBaseConnectionException;
import model.Flight;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModifyFlightPanel extends JPanel {
    private ApplicationController controller;
    private ModifyFlightForm modifyFlightForm;
    private FlightForm flightForm;

    public ModifyFlightPanel() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        setModifyFlightForm(new ModifyFlightForm(flightForm));
        this.setLayout(new BorderLayout());
        this.add(modifyFlightForm, BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton validate = new JButton("Modifier");
        validate.addActionListener(new ValidationListener());
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(validate, BorderLayout.CENTER);
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setModifyFlightForm(ModifyFlightForm modifyFlightForm) {
        this.modifyFlightForm = modifyFlightForm;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Flight flight = flightForm.getFlight();
            String originalFlightNumber = modifyFlightForm.getFlightComboBoxID();
            if (flight.getNumber().equals(originalFlightNumber)) {
                try {
                    controller.modifyFlight(flight);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (DataBaseConnectionException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    controller.modifyFlight(flight, originalFlightNumber);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (DataBaseConnectionException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

