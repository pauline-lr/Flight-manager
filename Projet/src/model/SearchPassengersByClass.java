package model;

import java.util.GregorianCalendar;

public class SearchPassengersByClass {
    private String passengerPassportNumber;
    private String passengerFirstName;
    private String passengerLastName;
    private Integer seatRow;
    private Character seatColumn;
    private String flightNumber;
    private GregorianCalendar flightDepartureTime;
    private GregorianCalendar flightArrivalTime;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCountry;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCountry;


    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setFlightDepartureTime(GregorianCalendar flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }
    public void setFlightArrivalTime(GregorianCalendar flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }
    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }
    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }
    public void setDepartureAirportCountry(String departureAirportCountry) {
        this.departureAirportCountry = departureAirportCountry;
    }
    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }
    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }
    public void setArrivalAirportCountry(String arrivalAirportCountry) {
        this.arrivalAirportCountry = arrivalAirportCountry;
    }
}
