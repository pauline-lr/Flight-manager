package model;

import exception.FlightException;

import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {

    //region Variables
    private final static String REGEX_NUMBER = "^[A-Z][A-Z]\\d{4}$";
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
    //endregion

    //region Constructors
    public Flight(String number, GregorianCalendar departureTime,
                  GregorianCalendar arrivalTime, Boolean isMealOnBoard, String mealDescription, String pilot, String departureGate, String arrivalGate, Integer plane)
            throws FlightException.NumberFlightException, FlightException.MealDescriptionException{
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
            if (matcher.find() /*&& ne se trouve pas déjà dans la BD*/) {
                this.number = number;
            } else {
                throw new FlightException.NumberFlightException(number);
            }
    }

    private void setDepartureTime(GregorianCalendar departureTime) {
        GregorianCalendar currentDate = new GregorianCalendar();
        if (departureTime.compareTo(currentDate) >= 0) {
            this.departureTime = departureTime;
        } else {
            try {
                throw new FlightException.DepartureDateException();
            } catch (FlightException.DepartureDateException | DateTimeParseException exception) {
                exception.printStackTrace();
            }
        }
    }
    private void setArrivalTime(GregorianCalendar arrivalTime) {
        if (arrivalTime.compareTo(departureTime) > 0) {
            this.arrivalTime = arrivalTime;
        } else {
            try {
                throw new FlightException.ArrivalDateException();
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
                    throw new FlightException.MealDescriptionException();
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

    //region Get
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
    public Integer getNumberPlane(){
        return plane;
    }
    public Integer getPlane() {
        return plane;
    }
    //endregion

    @Override
    public String toString() {
        return "number = " + number + '\n' +
                "departureTime = " + departureTime.getTime() + '\n' +
                "arrivalTime = " + arrivalTime.getTime() + '\n' +
                "isMealOnBoard = " + isMealOnBoard + '\n' +
                "mealDescription = " + mealDescription + '\n' +
                "pilot = " + pilot + '\n' +
                "departureGate = " + departureGate + '\n' +
                "arrivalGate = " + arrivalGate + '\n' +
                "plane = " + plane + '\n';
    }
}