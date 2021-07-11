package view.panel.edit;

import controller.ApplicationController;
import exception.FlightException;
import exception.NotMatchException;
import exception.TextLengthException;
import exception.dataBase.AddDataException;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import exception.dataBase.ModifyException;
import model.Flight;
import view.form.edit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddFlightPanel extends JPanel {
    private ApplicationController controller;
    private FlightForm flightForm;

    public AddFlightPanel() throws DataBaseConnectionException, AllDataException {
        setController(new ApplicationController());
        setFlightForm(new FlightForm());
        this.setLayout(new BorderLayout());
        this.add(flightForm, BorderLayout.CENTER);
        initializeValidationButton();
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    private void setFlightForm(FlightForm flightForm) {
        this.flightForm = flightForm;
    }

    private void initializeValidationButton() {
        JPanel buttonPanel = new JPanel();
        JButton validationButton = new JButton("Ajouter");

        validationButton.addActionListener(new ValidationButtonListener());
        buttonPanel.add(validationButton, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                if (!flightForm.checkFlightNumberIsExisting() && flightForm.getFlight() != null) {
                    Flight flight = flightForm.getFlight();
                    controller.addFlight(flight);
                    JOptionPane.showMessageDialog(null, "Vol ajouté", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DataBaseConnectionException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (AddDataException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (ModifyException e) {
                JOptionPane.showMessageDialog(null, e.getMessage( ), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.NumberFlightException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (FlightException.DepartureDateException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (TextLengthException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (NotMatchException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
