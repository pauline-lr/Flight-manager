package model;

import exception.FlightException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public Flight(String number, GregorianCalendar departureTime,
            GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, String pilot, String departureGate, String arrivalGate, Integer plane)
            throws FlightException.NumberFlightException, FlightException.MealDescriptionException{
        setNumber(number);
        // GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) cf. doc java
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setMealOnBoard(isMealOnBoard);
        setMealDescription(mealDescription);
        setPilot(pilot);
        setDepartureGate(departureGate);
        setArrivalGate(arrivalGate);
        setPlane(plane);
    }

    public Flight(String number,  GregorianCalendar departureTime,
                  GregorianCalendar arrivalTime, Boolean isMealOnBoard, String pilot, String departureGate, String arrivalGate, Integer plane)
            throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        this(number, departureTime, arrivalTime, isMealOnBoard, null, pilot, departureGate, arrivalGate, plane);
    }


    //endregion


    //region Setters
    private void setNumber(String number) throws FlightException.NumberFlightException {
            String patternNumberFlight = "\\b[A-z][A-z]\\d{4}$";
            Pattern r = Pattern.compile(patternNumberFlight);
            Matcher m = r.matcher(number);
            if (m.find())
                this.number = number;
            else
                throw new FlightException.NumberFlightException(number);
    }

    private void setDepartureTime(GregorianCalendar departureTime) {
        Calendar calendar = Calendar.getInstance();

        if(departureTime.YEAR >= calendar.get(Calendar.YEAR)) {
            this.departureTime = departureTime;
        }else{
            try {
                throw new FlightException.DepartureDateException(departureTime);
            } catch (FlightException.DepartureDateException e) {
                e.printStackTrace();
            }
        }
    }

    private void setArrivalTime(GregorianCalendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    private void setMealOnBoard(Boolean mealOnBoard) {
        isMealOnBoard = mealOnBoard;
    }
    public void setMealDescription(String mealDescription) throws FlightException.MealDescriptionException {
        if(mealDescription.length() <= 400)
            this.mealDescription = mealDescription;
        else
            throw new FlightException.MealDescriptionException(mealDescription);
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