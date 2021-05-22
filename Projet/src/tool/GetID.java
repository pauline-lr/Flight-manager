package tool;

import javax.swing.*;
import java.util.regex.*;

public class GetID {
    private final static String REGEX_ID = "^\\w+(?=\\s-\\s)";

    public static String getId(String text) {
        Pattern pattern = Pattern.compile(REGEX_ID);
        Matcher matcher = pattern.matcher(text);
        String id = null;
        if (matcher.find()) {
            id = matcher.group();
        }
        return id;
    }

    public static String getAirportId(JComboBox airportComboBox) {
        return getId((String) airportComboBox.getSelectedItem());
    }

    public static String getGateId(JComboBox airportComboBox, JComboBox terminalComboBox, JComboBox gateComboBox) {
        return (String) terminalComboBox.getSelectedItem() + gateComboBox.getSelectedItem() + getAirportId(airportComboBox);
    }

    public static Integer getPlaneId(JComboBox planeComboBox) {
        return Integer.parseInt(getId((String) planeComboBox.getSelectedItem()));
    }

    public static String getPilotId(JComboBox pilotComboBox) {
        return getId((String) pilotComboBox.getSelectedItem());
    }

    public static String getClassId(JComboBox classComboBox) {
        return getId((String) classComboBox.getSelectedItem());
    }
}
