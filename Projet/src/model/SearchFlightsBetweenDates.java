package model;

import java.util.GregorianCalendar;

public class SearchFlightsBetweenDates {
    private String flightNumber;
    private GregorianCalendar flightDepartureTime;
    private GregorianCalendar flightArrivalTime;
    private String departureGateTerminal;
    private Integer departureGateNumber;
    private String departureAirportCode;
    private String departureAirportName;
    private String departureAirportCountry;
    private String arrivalGateTerminal;
    private Integer arrivalGateNumber;
    private String arrivalAirportCode;
    private String arrivalAirportName;
    private String arrivalAirportCountry;
    private Integer planeId;
    private String planeModel;
    private String planeBrand;
    private String pilotLicenceNumber;
    private String pilotFirstName;
    private String pilotLastName;

    public SearchFlightsBetweenDates(String flightNumber, GregorianCalendar flightDepartureTime, GregorianCalendar flightArrivalTime, String departureGateTerminal, Integer departureGateNumber, String departureAirportCode, String departureAirportName, String departureAirportCountry, String arrivalGateTerminal, Integer arrivalGateNumber, String arrivalAirportCode, String arrivalAirportName, String arrivalAirportCountry, Integer planeId, String planeModel, String planeBrand, String pilotLicenceNumber, String pilotFirstName, String pilotLastName) {
        setFlightNumber(flightNumber);
        setFlightDepartureTime(flightDepartureTime);
        setFlightArrivalTime(flightArrivalTime);
        setDepartureGateTerminal(departureGateTerminal);
        setDepartureGateNumber(departureGateNumber);
        setDepartureAirportCode(departureAirportCode);
        setDepartureAirportName(departureAirportName);
        setDepartureAirportCountry(departureAirportCountry);
        setArrivalGateTerminal(arrivalGateTerminal);
        setArrivalGateNumber(arrivalGateNumber);
        setArrivalAirportCode(arrivalAirportCode);
        setArrivalAirportName(arrivalAirportName);
        setArrivalAirportCountry(arrivalAirportCountry);
        setPlaneId(planeId);
        setPlaneModel(planeModel);
        setPlaneBrand(planeBrand);
        setPilotLicenceNumber(pilotLicenceNumber);
        setPilotFirstName(pilotFirstName);
        setPilotLastName(pilotLastName);
    }

    //region Setters
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public void setFlightDepartureTime(GregorianCalendar flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }
    public void setFlightArrivalTime(GregorianCalendar flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }
    public void setDepartureGateTerminal(String departureGateTerminal) {
        this.departureGateTerminal = departureGateTerminal;
    }
    public void setDepartureGateNumber(Integer departureGateNumber) {
        this.departureGateNumber = departureGateNumber;
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
    public void setArrivalGateTerminal(String arrivalGateTerminal) {
        this.arrivalGateTerminal = arrivalGateTerminal;
    }
    public void setArrivalGateNumber(Integer arrivalGateNumber) {
        this.arrivalGateNumber = arrivalGateNumber;
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
    public void setPlaneId(Integer planeId) {
        this.planeId = planeId;
    }
    public void setPlaneModel(String planeModel) {
        this.planeModel = planeModel;
    }
    public void setPlaneBrand(String planeBrand) {
        this.planeBrand = planeBrand;
    }
    public void setPilotLicenceNumber(String pilotLicenceNumber) {
        this.pilotLicenceNumber = pilotLicenceNumber;
    }
    public void setPilotFirstName(String pilotFirstName) {
        this.pilotFirstName = pilotFirstName;
    }
    public void setPilotLastName(String pilotLastName) {
        this.pilotLastName = pilotLastName;
    }
    //endregion

    //region Getters
    public String getFlightNumber() {
        return flightNumber;
    }

    public GregorianCalendar getFlightDepartureTime() {
        return flightDepartureTime;
    }

    public GregorianCalendar getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public String getDepartureGateTerminal() {
        return departureGateTerminal;
    }

    public Integer getDepartureGateNumber() {
        return departureGateNumber;
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

    public String getArrivalGateTerminal() {
        return arrivalGateTerminal;
    }

    public Integer getArrivalGateNumber() {
        return arrivalGateNumber;
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

    public Integer getPlaneId() {
        return planeId;
    }

    public String getPlaneModel() {
        return planeModel;
    }

    public String getPlaneBrand() {
        return planeBrand;
    }

    public String getPilotLicenceNumber() {
        return pilotLicenceNumber;
    }

    public String getPilotFirstName() {
        return pilotFirstName;
    }

    public String getPilotLastName() {
        return pilotLastName;
    }

    //endregion
}
