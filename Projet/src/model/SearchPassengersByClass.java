package model;

import java.util.GregorianCalendar;

public class SearchPassengersByClass {
    private String passengerPassportNumber;
    private String passengerFirstName;
    private String passengerLastName;
    private Integer seatRow;
    private String seatColumn;
    private String flightNumber;
    private GregorianCalendar flightDepartureTime;
    private GregorianCalendar flightArrivalTime;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCountry;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCountry;

    public SearchPassengersByClass(String passengerPassportNumber, String passengerFirstName, String passengerLastName, Integer seatRow, String seatColumn, String flightNumber, GregorianCalendar flightDepartureTime, GregorianCalendar flightArrivalTime, String departureAirportCode, String departureAirportName, String departureAirportCountry, String arrivalAirportCode, String arrivalAirportName, String arrivalAirportCountry) {
        setPassengerPassportNumber(passengerPassportNumber);
        setPassengerFirstName(passengerFirstName);
        setPassengerLastName(passengerLastName);
        setSeatRow(seatRow);
        setSeatColumn(seatColumn);
        setFlightNumber(flightNumber);
        setFlightDepartureTime(flightDepartureTime);
        setFlightArrivalTime(flightArrivalTime);
        setDepartureAirportCode(departureAirportCode);
        setDepartureAirportName(departureAirportName);
        setDepartureAirportCountry(departureAirportCountry);
        setArrivalAirportCode(arrivalAirportCode);
        setArrivalAirportName(arrivalAirportName);
        setArrivalAirportCountry(arrivalAirportCountry);
    }

    public void setPassengerPassportNumber(String passengerPassportNumber) {
        this.passengerPassportNumber = passengerPassportNumber;
    }
    public void setPassengerFirstName(String passengerFirstName) {
        this.passengerFirstName = passengerFirstName;
    }
    public void setPassengerLastName(String passengerLastName) {
        this.passengerLastName = passengerLastName;
    }
    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }
    public void setSeatColumn(String seatColumn) {
        this.seatColumn = seatColumn;
    }
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
