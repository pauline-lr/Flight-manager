package model;

import exception.FlightException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gate {
    public final static String REGEX_TERMINAL = "^[A-Z]$";
    public final static String REGEX_NUMBER = "^\\d{2}$";

    private Character terminal;     // 1 character
    private Integer number;         // Max 2 positive digits
    private String airport;

    //region Constructors
    public Gate(Character terminal, Integer number, String airport)
            throws FlightException.TerminalException, FlightException.NumberGateException {
        setTerminal(terminal);
        setNumber(number);
        setAirport(airport);
    }
    //endregion

    //region Setters
    public void setTerminal(Character terminal) throws FlightException.TerminalException {
        Pattern r = Pattern.compile(REGEX_TERMINAL);
        Matcher m = r.matcher(String.valueOf(terminal));
        if (m.find())
            this.terminal = terminal;
        else
            throw new FlightException.TerminalException(terminal);
    }
    public void setNumber(Integer number) throws FlightException.NumberGateException {
        Pattern r = Pattern.compile(REGEX_NUMBER);
        Matcher m = r.matcher(String.valueOf(number));
        if (m.find())
            this.number = number;
        else
            throw new FlightException.NumberGateException(number);
    }
    public void setAirport(String airport) {
        this.airport = airport;
    }
    //endregion

    //region Getters
    //endregion
}
