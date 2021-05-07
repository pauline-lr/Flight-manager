package model;

import exception.FlightException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gate {
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
        String patternTerminal = "\\b[A-Z]$";
        Pattern r = Pattern.compile(patternTerminal);
        Matcher m = r.matcher(String.valueOf(terminal));
        if (m.find())
            this.terminal = terminal;
        else
            throw new FlightException.TerminalException(terminal);
    }
    public void setNumber(Integer number) throws FlightException.NumberGateException {
        String patternNumber = "^\\d{2}$";
        Pattern r = Pattern.compile(patternNumber);
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
