package userInterface.forms.searchForms;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class DateFlightForm extends JPanel {
    private JLabel start, end;
    private SpinnerDateModel spinnerModel;
    private JSpinner spinner;
    private Date date;

    public DateFlightForm(){
        this.setLayout(new GridLayout(8, 2, 5, 5));

        initForm();
    }

    void initForm(){
        start = new JLabel("Date de début : ");
        start.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(start);

        date = new Date();
        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new javax.swing.JSpinner(spinnerModel);
        JSpinner.DateEditor startDate = new JSpinner.DateEditor(spinner, "dd-MM-YYYY");
        spinner.setEditor(startDate);
        add(startDate);

        end = new JLabel("Date de fin : ");
        end.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(end);

        spinnerModel = new SpinnerDateModel(date, null, null, Calendar.DATE);
        spinner = new javax.swing.JSpinner(spinnerModel);
        JSpinner.DateEditor endDate = new JSpinner.DateEditor(spinner, "dd-MM-YYYY");
        spinner.setEditor(endDate);
        add(endDate);
    }
}
