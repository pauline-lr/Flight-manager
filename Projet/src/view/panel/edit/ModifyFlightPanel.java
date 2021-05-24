package view.panel.edit;

import exception.dataBase.DataBaseConnectionException;
import view.form.edit.FlightForm;
import view.form.edit.ModifyFlightForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModifyFlightPanel extends JPanel {
    private JButton validate;

    public ModifyFlightPanel() throws SQLException, DataBaseConnectionException {
        FlightForm flightForm = new FlightForm();
        this.setLayout(new BorderLayout());
        this.add(new ModifyFlightForm(flightForm), BorderLayout.PAGE_START);
        this.add(flightForm, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        validate = new JButton("Modifier");
        validate.addActionListener(new ValidationListener());
        this.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(validate, BorderLayout.CENTER);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // actions quand on clique sur le bouton "modifier"
        }
    }
}

