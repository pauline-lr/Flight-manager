package model;

import java.util.GregorianCalendar;

public class Flight {
    private String number;
    private GregorianCalendar departureTime;
    private GregorianCalendar arrivalTime;
    private Boolean isMealOnBoard;
    private String mealDescription;
    private Pilot pilot;
    private Gate departureGate;
    private Gate arrivalGate;
    private Plane plane;

    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, Pilot pilot, Gate departureGate, Gate arrivalGate, Plane plane) {
        this.number = number;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.isMealOnBoard = isMealOnBoard;
        this.mealDescription = mealDescription;
        this.pilot = pilot;
        this.departureGate = departureGate;
        this.arrivalGate = arrivalGate;
        this.plane = plane;
    }
}
