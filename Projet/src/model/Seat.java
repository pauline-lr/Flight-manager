package model;

import exception.NotMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Seat {
    private final static String REGEX_SEAT_ROW = "^\\d{1,3}$";
    private final static String REGEX_SEAT_COLUMN = "^[A-Z]$";

    private Integer seatRow;            // Max 3 positive digits
    private Character seatColumn;       // 1 letter
    private Boolean isOnWindowSide;
    private String passenger;        // Can be null
    private String flight;
    private Integer seatClass;

    //region Constructors
    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, String passenger, String flight, Integer seatClass)
            throws NotMatchException {
        setSeatRow(seatRow);
        setSeatColumn(seatColumn);
        setOnWindowSide(isOnWindowSide);
        setPassenger(passenger);
        setFlight(flight);
        setSeatClass(seatClass);
    }

    public Seat(Integer seatRow, Character seatColumn, Boolean isOnWindowSide, String flight, Integer seatClass)
            throws NotMatchException {
        this(seatRow, seatColumn, isOnWindowSide, null, flight, seatClass);
    }
    //endregion

    //region Setters
    private void setSeatRow(Integer seatRow) throws  NotMatchException {
        Pattern pattern = Pattern.compile(REGEX_SEAT_ROW);
        Matcher matcher = pattern.matcher(Integer.toString(seatRow));
        if (matcher.find()) {
            this.seatRow = seatRow;
        } else {
            throw new NotMatchException("Le numéro de ligne d'une place", "suite de 3 chiffres");
        }
    }

    private void setSeatColumn(Character seatColumn) throws NotMatchException {
        Pattern pattern = Pattern.compile(REGEX_SEAT_COLUMN);
        Matcher matcher = pattern.matcher(Character.toString(seatColumn));
        if (matcher.find()) {
            this.seatColumn = seatColumn;
        } else {
            throw new NotMatchException("Le numéro de colonne d'une place", "1 lettre majuscule");
        }
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
