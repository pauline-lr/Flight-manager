package view.forms.searchForms;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SearchFlightsBetweenDatesForm extends JPanel {
    private GregorianCalendar firstDateCalendar;
    private GregorianCalendar lastDateCalendar;
    private JLabel start, end;
    private JSpinner startDay, startMonth, startYear;
    private JSpinner endDay, endMonth, endYear;
    private SpinnerNumberModel startDayModel, startMonthModel, startYearModel;
    private SpinnerNumberModel endDayModel, endMonthModel, endYearModel;
    private GregorianCalendar currentDate;

    public SearchFlightsBetweenDatesForm(){
        currentDate = new GregorianCalendar();
        this.setLayout(new GridLayout(2, 4));

        createFlightsBetweenDatesForm();
    }

    void createFlightsBetweenDatesForm(){
        start = new JLabel("Date de début : ");
        start.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(start);

        startDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH),1,31,1);
        startDay = new JSpinner(startDayModel);
        add(startDay);

        startMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH)+1,1,12,1);
        startMonth = new JSpinner(startMonthModel);
        add(startMonth);

        startYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR),2021,2023,1);
        startYear = new JSpinner(startYearModel);
        add(startYear);

        firstDateCalendar = new GregorianCalendar(Integer.parseInt(startYear.getValue().toString()),
                Integer.parseInt(startMonth.getValue().toString()), Integer.parseInt(startDay.getValue().toString()));

        end = new JLabel("Date de fin : ");
        end.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(end);

        endDayModel = new SpinnerNumberModel(currentDate.get(Calendar.DAY_OF_MONTH),1,31,1);
        endDay = new JSpinner(endDayModel);
        add(endDay);

        endMonthModel = new SpinnerNumberModel(currentDate.get(Calendar.MONTH)+1,1,12,1);
        endMonth = new JSpinner(endMonthModel);
        add(endMonth);

        endYearModel = new SpinnerNumberModel(currentDate.get(Calendar.YEAR),2021,2023,1);
        endYear = new JSpinner(endYearModel);
        add(endYear);

        lastDateCalendar = new GregorianCalendar(Integer.parseInt(endYear.getValue().toString()),
                Integer.parseInt(endMonth.getValue().toString()), Integer.parseInt(endDay.getValue().toString()));
    }

    public GregorianCalendar getFirstDateCalendar() {
        return firstDateCalendar;
    }

    public GregorianCalendar getLastDateCalendar() {
        return lastDateCalendar;
    }
}
