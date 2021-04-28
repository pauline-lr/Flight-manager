package model;

public class Seat {
    private Integer seatRow;            // Max 3 positive digits
    private Character seatColumn;       // 1 letter
    private Boolean isOnWindowSide;
    private String passenger;        // Can be null
    private String flight;
    private Integer seatClass;

    //region Constructors
    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, String passenger, String flight, Integer seatClass) {
        setSeatRow(seatRow);
        setSeatColumn(seatColumn);
        setOnWindowSide(isOnWindowSide);
        setPassenger(passenger);
        setFlight(flight);
        setSeatClass(seatClass);
    }
    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, String flight, Integer seatClass) {
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
    private void setPassenger(String passenger) {
        this.passenger = passenger;
    }
    private void setFlight(String flight) {
        this.flight = flight;
    }
    private void setSeatClass(Integer seatClass) {
        this.seatClass = seatClass;
    }
    //endregion

    //region Getters
    //endregion
}
