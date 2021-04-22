package model;

public class Gate {
    private Character terminal;
    private Integer number;
    private Airport airport;

    public Gate(Character terminal, Integer number, Airport airport) {
        this.terminal = terminal;
        this.number = number;
        this.airport = airport;
    }
}
