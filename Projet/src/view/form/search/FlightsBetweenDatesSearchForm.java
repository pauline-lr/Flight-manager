package view.form.search;

import tool.Format;

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
        this.setLayout(new GridLayout(6, 1));

        createFlightsBetweenDatesForm();
    }

    public GregorianCalendar getStartDate() {
        return Format.getDate(startDate);
    }

    public GregorianCalendar getEndDate() {
        return Format.getDate(endDate);
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    void createFlightsBetweenDatesForm(){
        JLabel titleLabel = new JLabel("Rechercher les vols entre deux dates");
        titleLabel.setFont(Format.bigTitleFont);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        JLabel start = new JLabel("    Choisissez la date de début");
        start.setFont(Format.titleFont);
        start.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(start);

        startDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        startDate.setEditor(new JSpinner.DateEditor(startDate, "dd/MM/yyyy"));
        this.add(startDate);

        JLabel end = new JLabel("    Choisissez la date de fin");
        end.setFont(Format.titleFont);
        end.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(end);

        endDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        endDate.setEditor(new JSpinner.DateEditor(endDate, "dd/MM/yyyy"));
        this.add(endDate);
    }
}
