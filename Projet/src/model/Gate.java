package model;

public class Gate {
    private Character terminal;     // 1 character
    private Integer number;         // Max 2 positive digits
    private Airport airport;

    //region Constructors
    public Gate(Character terminal, Integer number, Airport airport) {
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
    public void setAirport(Airport airport) {
        this.airport = airport;
    }
    //endregion

    //region Getters
    //endregion
}
