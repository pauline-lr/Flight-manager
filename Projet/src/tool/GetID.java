package tool;

import javax.swing.*;
import java.util.regex.*;

public class GetID {
    private final static String REGEX_ID = "^\\w+(?=\\s-\\s)";

    private static String getID(String text) {
        Pattern pattern = Pattern.compile(REGEX_ID);
        Matcher matcher = pattern.matcher(text);
        String id = null;
        if (matcher.find()) {
            id = matcher.group();
        }
        return id;
    }

    public static String getFlightID(JComboBox flightComboBox) {
        return getID((String) flightComboBox.getSelectedItem());
    }

    public static String getAirportID(JComboBox airportComboBox) {
        return getID((String) airportComboBox.getSelectedItem());
    }

    public static String getGateID(JComboBox airportComboBox, JComboBox terminalComboBox, JComboBox gateComboBox) {
        return (String) terminalComboBox.getSelectedItem() + gateComboBox.getSelectedItem() + getAirportID(airportComboBox);
    }

    public static Integer getPlaneID(JComboBox planeComboBox) {
        return Integer.parseInt(getID((String) planeComboBox.getSelectedItem()));
    }

    public static String getPilotID(JComboBox pilotComboBox) {
        return getID((String) pilotComboBox.getSelectedItem());
    }
}