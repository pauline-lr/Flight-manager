package model;

public class Seat {
    private Integer seatRow;
    private Character seatColumn;
    private Boolean isOnWindowSide;
    private Passenger passenger;
    private Flight flight;
    private Class seatClass;

    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, Passenger passenger, Flight flight, Class seatClass) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.isOnWindowSide = isOnWindowSide;
        this.passenger = passenger;
        this.flight = flight;
        this.seatClass = seatClass;
    }
}
