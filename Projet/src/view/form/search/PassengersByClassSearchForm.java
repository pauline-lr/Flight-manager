package view.form.search;

import controller.ApplicationController;
import exception.RetrievalException;
import exception.ConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;

public class PassengersByClassSearchForm extends JPanel {
    private ApplicationController controller;
    private JComboBox<Object> classComboBox;

    public PassengersByClassSearchForm() throws ConnectionException, RetrievalException {
        setController(new ApplicationController());
        this.setLayout(new GridLayout(4, 2));

        createPassengersByClassForm();
    }

    public JComboBox<Object> getClassComboBox() {
        return classComboBox;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void createPassengersByClassForm() throws ConnectionException, RetrievalException {
        JLabel titleLabel = new JLabel("Rechercher les passagers d'une classe");
        titleLabel.setFont(Format.bigTitleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        JLabel classLabel = new JLabel("    Choisissez la classe");
        classLabel.setFont(Format.titleFont);
        classLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(classLabel);

        classComboBox = new JComboBox<>(controller.getAllClassesToString().toArray());
        this.add(classComboBox);
    }
}