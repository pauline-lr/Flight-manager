package model;

import exception.FlightException;

import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// NB : j'ai changé les types d'objets qui étaient en String par leur objet car j'en avais besoin pour le getters 
public class Flight {
    public final static String REGEX_NUMBER = "^[A-Z][A-Z]\\d{4}$";
    private final static int MEAL_DESCRIPTION_LENTGH = 400;

    private String number;                      // 6 characters = 2 letters then 4 positive digits
    private GregorianCalendar departureTime;    // JJ/MM/AAAA HH:MM ( > today)
    private GregorianCalendar arrivalTime;      // JJ/MM/AAAA HH:MM ( > today)
    private Boolean isMealOnBoard;
    private String mealDescription;             // Max 400 characters - can be null
    private String pilot;
    private String departureGate;
    private String arrivalGate;
    private Integer plane;
    private final GregorianCalendar currentDate;

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
        currentDate = new GregorianCalendar();
    }
    public Flight(String number, GregorianCalendar departureTime,
                  GregorianCalendar arrivalTime, Boolean isMealOnBoard, String pilot, String departureGate, String arrivalGate, Integer plane)
            throws FlightException.NumberFlightException, FlightException.MealDescriptionException {
        this(number, departureTime, arrivalTime, isMealOnBoard, null, pilot, departureGate, arrivalGate, plane);
    }
    //endregion

    //region Setters
    private void setNumber(String number) throws FlightException.NumberFlightException {
            Pattern pattern = Pattern.compile(REGEX_NUMBER);
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                this.number = number;
            } else {
                throw new FlightException.NumberFlightException(number);
            }
    }
    private void setDepartureTime(GregorianCalendar departureTime) {
        this.departureTime = departureTime;
        Calendar departureTimeCalendar = departureTime;
        Calendar currentDateC = currentDate;
        if (departureTimeCalendar.compareTo(currentDateC) > 0) {
            this.departureTime = departureTime;
        } else {
            try {
                throw new FlightException.DepartureDateException(departureTime);
            } catch (FlightException.DepartureDateException | DateTimeParseException exception) {
                exception.printStackTrace();
            }
        }
    }
    private void setArrivalTime(GregorianCalendar arrivalTime) {
        this.arrivalTime = arrivalTime;
        Calendar arrivalTimeCalendar = departureTime;
        Calendar departureTimeCalendar = departureTime;
        if (arrivalTimeCalendar.compareTo(departureTimeCalendar) > 0) {
            this.arrivalTime = arrivalTime;
        } else {
            try {
                throw new FlightException.ArrivalDateException(arrivalTime);
            } catch (FlightException.ArrivalDateException | DateTimeParseException exception) {
                exception.printStackTrace();
            }
        }
    }
    private void setMealOnBoard(Boolean mealOnBoard) {
        isMealOnBoard = mealOnBoard;
    }
    public void setMealDescription(String mealDescription) throws FlightException.MealDescriptionException {
        if (mealDescription != null) {
            if (!(mealDescription.equals(""))) {
                if (mealDescription.length() <= MEAL_DESCRIPTION_LENTGH) {
                    this.mealDescription = mealDescription;
                } else {
                    throw new FlightException.MealDescriptionException(mealDescription);
                }
            } else {
                this.mealDescription = null;
            }
        }
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
        // on obtient la clé (en string) pour rechercher pilot dans la BD
        return pilot;
    }
    public String getDepartureGate() {
        return departureGate;
    }
    public String getArrivalGate() {
        return arrivalGate;
    }
    public Integer getNumberPlane(){
        return plane;
    }
    public Integer getPlane() {
        return plane;
    }
    //endregion
}