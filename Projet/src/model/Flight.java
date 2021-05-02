package model;

import java.util.GregorianCalendar;

public class Flight {
    private String number;                      // 6 characters = 2 letters then 4 positive digits
    private GregorianCalendar departureTime;    // JJ/MM/AAAA HH:MM ( > today)
    private GregorianCalendar arrivalTime;      // JJ/MM/AAAA HH:MM ( > today)
    private Boolean isMealOnBoard;
    private String mealDescription;             // Max 400 characters - can be null
    private String pilot;
    private String departureGate;
    private String arrivalGate;
    private Integer plane;

    //region Constructors
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, String pilot, String departureGate, String arrivalGate, Integer plane) {
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
    public Flight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, Boolean isMealOnBoard, String pilot, String departureGate, String arrivalGate, Integer plane) {
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
    private void setPilot(String pilot) {
        this.pilot = pilot;
    }
    private void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }
    private void setArrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
    }
    private void setPlane(Integer plane) {
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
    public String getPilot() {
        return pilot;
    }
    public String getDepartureGate() {
        return departureGate;
    }
    public String getArrivalGate() {
        return arrivalGate;
    }
    public Integer getPlane() {
        return plane;
    }
    //endregion
}