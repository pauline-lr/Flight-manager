package view.form.search;

import controller.ApplicationController;
import exception.dataBase.AllDataException;
import exception.dataBase.DataBaseConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;

public class PassengersByClassSearchForm extends JPanel {
    private ApplicationController controller;
    private JComboBox<Object> classComboBox;

    public PassengersByClassSearchForm() throws DataBaseConnectionException, AllDataException {
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

    public void createPassengersByClassForm() throws DataBaseConnectionException, AllDataException {
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