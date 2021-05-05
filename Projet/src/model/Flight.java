package model;

import exception.NumberFlightException;

import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// est-ce qu'il ne manquerait pas terminal ?

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
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, Pilot pilot, Gate departureGate, Gate arrivalGate, Plane plane)
            throws NumberFlightException {
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
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, Pilot pilot, Gate departureGate, Gate arrivalGate, Plane plane)
            throws NumberFlightException {
        this(number, departureTime, arrivalTime, isMealOnBoard, null, pilot, departureGate, arrivalGate, plane);
    }
    //endregion


    //region Setters
    private void setNumber(String number) throws NumberFlightException {
            String patternNumberFlight = "\\b[A-z][A-z]\\d{4}$";
            Pattern r = Pattern.compile(patternNumberFlight);
            Matcher m = r.matcher(number);
            if (m.find())
                this.number = number;
            else
                throw new NumberFlightException(number);
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
    public String getNumber() {
        return number;
    }
    public GregorianCalendar getDepartureTime() {
        return departureTime;
    }
    public GregorianCalendar getArrivalTime() {
        return arrivalTime;
    }
    public Boolean getMealOnBoard() {
        return isMealOnBoard;
    }
    public String getMealDescription() {
        return mealDescription;
    }
    public Pilot getPilot() {
        return pilot;
    }
    public Gate getDepartureGate() {
        return departureGate;
    }
    public Gate getArrivalGate() {
        return arrivalGate;
    }
    public Plane getPlane() {
        return plane;
    }
    //endregion
}