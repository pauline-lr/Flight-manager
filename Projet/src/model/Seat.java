package model;

public class Seat {
    private Integer seatRow;            // Max 3 positive digits
    private Character seatColumn;       // 1 letter
    private Boolean isOnWindowSide;
    private Passenger passenger;        // Can be null
    private Flight flight;
    private Class seatClass;

    //region Constructors
    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, Passenger passenger, Flight flight, Class seatClass) {
        setSeatRow(seatRow);
        setSeatColumn(seatColumn);
        setOnWindowSide(isOnWindowSide);
        setPassenger(passenger);
        setFlight(flight);
        setSeatClass(seatClass);
    }
    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, Flight flight, Class seatClass) {
        this(seatRow, seatColumn, isOnWindowSide, null, flight, seatClass);
    }
    //endregion

    //region Setters
    private void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }
    private void setSeatColumn(Character seatColumn) {
        this.seatColumn = seatColumn;
    }
    private void setOnWindowSide(Boolean onWindowSide) {
        isOnWindowSide = onWindowSide;
    }
    private void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
    private void setFlight(Flight flight) {
        this.flight = flight;
    }
    private void setSeatClass(Class seatClass) {
        this.seatClass = seatClass;
    }
    //endregion

    //region Getters
    //endregion
}
