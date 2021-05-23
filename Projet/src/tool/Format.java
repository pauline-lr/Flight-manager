package tool;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Format {
    public static final Font font = new Font(null, Font.BOLD, 13);

    public static String timeFormat(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setCalendar(calendar);

        return format.format(calendar.getTime());
    }

    public static String dateFormat(GregorianCalendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setCalendar(calendar);

        return format.format(calendar.getTime());
    }

    public static GregorianCalendar getDate(JSpinner dateSpinner) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Date date = (Date) dateSpinner.getValue();
        gregorianCalendar.setTime(date);
        return gregorianCalendar;
    }
}
