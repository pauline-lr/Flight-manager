package model;

import exception.NotMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gate {
    private final static String REGEX_TERMINAL = "^[A-Z]$";
    private final static String REGEX_NUMBER = "^\\d{1,2}$";

    private Integer gateID;
    private Character terminal;     // 1 character
    private Integer number;         // Max 2 positive digits
    private String airport;

    //region Constructors
    public Gate(Integer gateID, Character terminal, Integer number, String airport)
            throws NotMatchException {
        setGateID(gateID);
        setTerminal(terminal);
        setNumber(number);
        setAirport(airport);
    }
    //endregion

    //region Setters
    public void setGateID(Integer gateID) {
        this.gateID = gateID;
    }

    public void setTerminal(Character terminal) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_TERMINAL);
        Matcher m = r.matcher(String.valueOf(terminal));
        if (!m.find())
            throw new NotMatchException("Le terminal", "une lettre majuscule");
        else
            this.terminal = terminal;
    }
    public void setNumber(Integer number) throws NotMatchException {
        Pattern r = Pattern.compile(REGEX_NUMBER);
        Matcher m = r.matcher(String.valueOf(number));
        if (!m.find())
            throw new NotMatchException("Le numéro de porte", "2 chiffres");
        else
            this.number = number;
    }
    public void setAirport(String airport) {
        this.airport = airport;
    }
    //endregion
}
