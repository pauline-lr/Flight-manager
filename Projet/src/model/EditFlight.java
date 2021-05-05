package model;

import java.util.GregorianCalendar;

public class EditFlight {
    private String number;
    private GregorianCalendar departureTime;
    private GregorianCalendar arrivalTime;
    private String departureAirportCode;
    private String departureGateTerminal;
    private Integer departureGateNumber;
    private String arrivalAirportCode;
    private String arrivalGateTerminal;
    private Integer arrivalGateNumber;
    private Integer plane;
    private String pilot;
    private Boolean isMealOnBoard;
    private String mealDescription;

    public EditFlight(String number, GregorianCalendar departureTime, GregorianCalendar arrivalTime, String departureAirportCode, String departureGateTerminal, Integer departureGateNumber, String arrivalAirportCode, String arrivalGateTerminal, Integer arrivalGateNumber, Integer plane, String pilot, Boolean isMealOnBoard, String mealDescription) {
        setNumber(number);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setDepartureAirportCode(departureAirportCode);
        setDepartureGateTerminal(departureGateTerminal);
        setDepartureGateNumber(departureGateNumber);
        setArrivalAirportCode(arrivalAirportCode);
        setArrivalGateTerminal(arrivalGateTerminal);
        setArrivalGateNumber(arrivalGateNumber);
        setPlane(plane);
        setPilot(pilot);
        setMealOnBoard(isMealOnBoard);
        setMealDescription(mealDescription);
    }

    private void setNumber(String number) {
        this.number = number;
    }
    private void setDepartureTime(GregorianCalendar departureTime) {
        this.departureTime = departureTime;
    }
    private void setArrivalTime(GregorianCalendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    private void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }
    private void setDepartureGateTerminal(String departureGateTerminal) {
        this.departureGateTerminal = departureGateTerminal;
    }
    private void setDepartureGateNumber(Integer departureGateNumber) {
        this.departureGateNumber = departureGateNumber;
    }
    private void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }
    private void setArrivalGateTerminal(String arrivalGateTerminal) {
        this.arrivalGateTerminal = arrivalGateTerminal;
    }
    private void setArrivalGateNumber(Integer arrivalGateNumber) {
        this.arrivalGateNumber = arrivalGateNumber;
    }
    private void setPlane(Integer plane) {
        this.plane = plane;
    }
    private void setPilot(String pilot) {
        this.pilot = pilot;
    }
    private void setMealOnBoard(Boolean mealOnBoard) {
        isMealOnBoard = mealOnBoard;
    }
    private void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }

    public String getNumber() {
        return number;
    }
    public GregorianCalendar getDepartureTime() {
        return departureTime;
    }
    public GregorianCalendar getArrivalTime() {
        return arrivalTime;
    }
    public String getDepartureAirportCode() {
        return departureAirportCode;
    }
    public String getDepartureGateTerminal() {
        return departureGateTerminal;
    }
    public Integer getDepartureGateNumber() {
        return departureGateNumber;
    }
    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }
    public String getArrivalGateTerminal() {
        return arrivalGateTerminal;
    }
    public Integer getArrivalGateNumber() {
        return arrivalGateNumber;
    }
    public Integer getPlane() {
        return plane;
    }
    public String getPilot() {
        return pilot;
    }
    public Boolean getMealOnBoard() {
        return isMealOnBoard;
    }
    public String getMealDescription() {
        return mealDescription;
    }
}
