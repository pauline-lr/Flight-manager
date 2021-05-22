package view.form.search;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FlightsBetweenDatesSearchForm extends JPanel {
    private GregorianCalendar firstDateCalendar, lastDateCalendar;
    private JLabel start, end;
    private JSpinner startDate, endDate;
    private Date currentDate;

    public FlightsBetweenDatesSearchForm(){
        currentDate = new Date();
        this.setLayout(new GridLayout(2, 4));

        createFlightsBetweenDatesForm();
    }

    void createFlightsBetweenDatesForm(){
        start = new JLabel("Date de début : ");
        start.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(start);

        startDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        startDate.setEditor(new JSpinner.DateEditor(startDate, "dd-MM-yyyy"));
        this.add(startDate);

        GregorianCalendar firstDateGC = (GregorianCalendar) startDate.getValue();
        firstDateCalendar = new GregorianCalendar(firstDateGC.YEAR, firstDateGC.MONTH, firstDateGC.DAY_OF_WEEK);


        end = new JLabel("Date de fin : ");
        end.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(end);

        endDate = new JSpinner(new SpinnerDateModel(currentDate, null, null, Calendar.DAY_OF_WEEK));
        endDate.setEditor(new JSpinner.DateEditor(endDate, "dd-MM-yyyy"));
        this.add(endDate);

        GregorianCalendar lastDateGC = (GregorianCalendar) endDate.getValue();
        lastDateCalendar = new GregorianCalendar(lastDateGC.YEAR, lastDateGC.MONTH, lastDateGC.DAY_OF_WEEK);
    }

    public GregorianCalendar getFirstDateCalendar() {
        return firstDateCalendar;
    }

    public GregorianCalendar getLastDateCalendar() {
        return lastDateCalendar;
    }
}
