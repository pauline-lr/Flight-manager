package view.forms.searchForms;

import controller.ApplicationController;
import exception.DataBaseConnectionException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPassengersByClassForm extends JPanel {
    private ApplicationController controller;
    private JLabel classLabel;
    private JComboBox classComboBox;
    private Font font = new Font(null, Font.BOLD, 13);

    public SearchPassengersByClassForm() throws SQLException, DataBaseConnectionException {
        this.controller = controller;
        this.setLayout(new GridLayout(14, 2, 5, 5));

        createPassengersByClassForm();
    }

    public void createPassengersByClassForm() throws SQLException, DataBaseConnectionException {
        classLabel = new JLabel("    Choisissez la classe");
        classLabel.setFont(font);
        classLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(classLabel);

        classComboBox = new JComboBox(controller.getAllClassesForComboBox());
        this.add(classComboBox);
    }

    public String getClassId() {
        String classText = (String) classComboBox.getSelectedItem();
        return getId(classText);
    }

    public String getId(String text) {
        Pattern pattern = Pattern.compile("^\\w+(?=\\s-\\s)");
        Matcher matcher = pattern.matcher(text);
        String id = null;
        if (matcher.find()) {
            id = matcher.group();
        }
        return id;
    }
}