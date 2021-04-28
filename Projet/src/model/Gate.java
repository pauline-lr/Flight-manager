package model;

public class Gate {
    private Character terminal;     // 1 character
    private Integer number;         // Max 2 positive digits
    private String airport;

    //region Constructors
    public Gate(Character terminal, Integer number, String airport) {
        setTerminal(terminal);
        setNumber(number);
        setAirport(airport);
    }
    //endregion

    //region Setters
    public void setTerminal(Character terminal) {
        this.terminal = terminal;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setAirport(String airport) {
        this.airport = airport;
    }
    //endregion

    //region Getters
    //endregion
}
