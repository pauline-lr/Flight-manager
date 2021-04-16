package modelPackage;

import java.util.GregorianCalendar;

public class Flight {
    private Integer number;
    private Integer flightTime;
    private GregorianCalendar departureTime;
    private Boolean isMealOnBoard;
    private String mealDescription;
    private String departureAirportID;
    private String arrivalAirportID;
    private String pilotID;
    private String planeID;


    public Flight(Integer number, Integer flightTime, GregorianCalendar departureTime, Boolean isMealOnBoard, String mealDescription, String departureAirportID, String arrivalAirportID, String pilotID, String planeID) {
        this.number = number;
        this.flightTime = flightTime;
        this.departureTime = departureTime;
        this.isMealOnBoard = isMealOnBoard;
        this.mealDescription = mealDescription;
        this.departureAirportID = departureAirportID;
        this.arrivalAirportID = arrivalAirportID;
        this.pilotID = pilotID;
        this.planeID = planeID;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getFlightTime() {
        return flightTime;
    }

    public GregorianCalendar getDepartureTime() {
        return departureTime;
    }

    public Boolean getMealOnBoard() {
        return isMealOnBoard;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public String getDepartureAirportID() {
        return departureAirportID;
    }

    public String getArrivalAirportID() {
        return arrivalAirportID;
    }

    public String getPilotID() {
        return pilotID;
    }

    public String getPlaneID() {
        return planeID;
    }
}
