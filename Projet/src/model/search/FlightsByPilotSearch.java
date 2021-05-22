package model.search;

import java.util.GregorianCalendar;

public class FlightsByPilotSearch {
    private String flightNumber;
    private GregorianCalendar flightDepartureTime;
    private GregorianCalendar flightArrivalTime;
    private Integer planeId;
    private String planeModel;
    private String planeBrand;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCountry;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCountry;

    public FlightsByPilotSearch(String flightNumber, GregorianCalendar flightDepartureTime, GregorianCalendar flightArrivalTime, Integer planeId, String planeModel, String planeBrand, String departureAirportCode, String departureAirportName, String departureAirportCountry, String arrivalAirportCode, String arrivalAirportName, String arrivalAirportCountry) {
        setFlightNumber(flightNumber);
        setFlightDepartureTime(flightDepartureTime);
        setFlightArrivalTime(flightArrivalTime);
        setPlaneId(planeId);
        setPlaneModel(planeModel);
        setPlaneBrand(planeBrand);
        setDepartureAirportCode(departureAirportCode);
        setDepartureAirportName(departureAirportName);
        setDepartureAirportCountry(departureAirportCountry);
        setArrivalAirportCode(arrivalAirportCode);
        setArrivalAirportName(arrivalAirportName);
        setArrivalAirportCountry(arrivalAirportCountry);
    }

    //refion Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public GregorianCalendar getFlightDepartureTime() {
        return flightDepartureTime;
    }

    public GregorianCalendar getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public Integer getPlaneId() {
        return planeId;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public String getPlaneBrand() {
        return planeBrand;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public String getDepartureAirportCountry() {
        return departureAirportCountry;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public String getArrivalAirportCountry() {
        return arrivalAirportCountry;
    }

    //endregion

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setFlightDepartureTime(GregorianCalendar flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }
    public void setFlightArrivalTime(GregorianCalendar flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }
    public void setPlaneId(Integer planeId) {
        this.planeId = planeId;
    }
    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
    }
    public void setPlaneBrand(String planeBrand) {
        this.planeBrand = planeBrand;
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