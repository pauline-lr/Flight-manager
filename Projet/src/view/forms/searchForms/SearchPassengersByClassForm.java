package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;
import tool.Format;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SearchPassengersByClassForm extends JPanel {
    private ApplicationController controller;
    private JLabel classLabel;
    private JComboBox<String> classComboBox;

    public SearchPassengersByClassForm() throws SQLException, DataBaseConnectionException {
        setController(new ApplicationController());
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createPassengersByClassForm();
    }

    public JComboBox<String> getClassComboBox() {
        return classComboBox;
    }

    private void setController(ApplicationController controller) {
        this.controller = controller;
    }

    public void createPassengersByClassForm() throws SQLException, DataBaseConnectionException {
        classLabel = new JLabel("    Choisissez la classe");
        classLabel.setFont(Format.font);
        classLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(classLabel);

        classComboBox = new JComboBox<>(controller.getAllClassesForComboBox());
        this.add(classComboBox);
    }
}