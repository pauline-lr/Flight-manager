package model;

import java.util.GregorianCalendar;

public class Flight {
    private String number;                      // 6 characters = 2 letters then 4 positive digits
    private GregorianCalendar departureTime;    // JJ/MM/AAAA HH:MM ( > today)
    private GregorianCalendar arrivalTime;      // JJ/MM/AAAA HH:MM ( > today)
    private Boolean isMealOnBoard;
    private String mealDescription;             // Max 400 characters - can be null
    private Pilot pilot;
    private Gate departureGate;
    private Gate arrivalGate;
    private Plane plane;

    //region Constructors
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, Pilot pilot, Gate departureGate, Gate arrivalGate, Plane plane) {
        setNumber(number);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setMealOnBoard(isMealOnBoard);
        setMealDescription(mealDescription);
        setPilot(pilot);
        setDepartureGate(departureGate);
        setArrivalGate(arrivalGate);
        setPlane(plane);
    }
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, Pilot pilot, Gate departureGate, Gate arrivalGate, Plane plane) {
        this(number, departureTime, arrivalTime, isMealOnBoard, null, pilot, departureGate, arrivalGate, plane);
    }
    //endregion

    //region Setters
    private void setNumber(String number) {
        this.number = number;
    }
    private void setDepartureTime(GregorianCalendar departureTime) {
        this.departureTime = departureTime;
    }
    private void setArrivalTime(GregorianCalendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    private void setMealOnBoard(Boolean mealOnBoard) {
        isMealOnBoard = mealOnBoard;
    }
    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }
    private void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    private void setDepartureGate(Gate departureGate) {
        this.departureGate = departureGate;
    }
    private void setArrivalGate(Gate arrivalGate) {
        this.arrivalGate = arrivalGate;
    }
    private void setPlane(Plane plane) {
        this.plane = plane;
    }
    //endregion

    //region Getters
    //endregion
}