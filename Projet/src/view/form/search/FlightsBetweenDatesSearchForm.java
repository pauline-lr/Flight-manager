package view.form.search;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FlightsBetweenDatesSearchForm extends JPanel {
    private JSpinner startDate, endDate;
    private Date currentDate;

    public FlightsBetweenDatesSearchForm(){
        setCurrentDate(new Date());
        this.setLayout(new GridLayout(2, 4));

        createFlightsBetweenDatesForm();
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    void createFlightsBetweenDatesForm(){
        JLabel start = new JLabel("Date de début : ");
        start.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(start);

        startDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        startDate.setEditor(new JSpinner.DateEditor(startDate, "dd/MM/yyyy"));
        this.add(startDate);

        JLabel end = new JLabel("Date de fin : ");
        end.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(end);

        endDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        endDate.setEditor(new JSpinner.DateEditor(endDate, "dd/MM/yyyy"));
        this.add(endDate);
    }

    public GregorianCalendar getStartDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date date = (Date) startDate.getValue();
        gregorianCalendar.setTime(date);
        return gregorianCalendar;
    }

    public GregorianCalendar getEndDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date date = (Date) endDate.getValue();
        gregorianCalendar.setTime(date);
        return gregorianCalendar;
    }
}
