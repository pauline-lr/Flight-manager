package controller;

import business.FlightManager;
import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

import java.util.*;

public class ApplicationController {
    private FlightManager flightManager;

    public ApplicationController() {
        setFlightManager(new FlightManager());
    }

    private void setFlightManager(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    //region Get
    public Flight getFlight(String flightNumber)
            throws AllDataException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws  AllDataException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws  AllDataException, DataBaseConnectionException {
        return flightManager.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getGateToString(gateID);
    }

    public String[] getAllFlightsToString()
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllFlightsToString();
    }

    public String[] getAllPilotsToString()
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllPilotsToString();
    }

    public String[] getAllPlanesAvailableToString()
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllPlanesAvailableToString();
    }

    public String[] getAllClassesToString()
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllClassesToString();
    }

    public String[] getAllAirportsToString()
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllAirportsToString();
    }

    public String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws AllDataException,   DataBaseConnectionException {
        return flightManager.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws AllDataException,  DataBaseConnectionException {
        return flightManager.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws  AllDataException,  DataBaseConnectionException {
        return flightManager.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws  AllDataException,  DataBaseConnectionException {
        return flightManager.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws  AllDataException,  DataBaseConnectionException {
        return flightManager.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws AddDataException, DataBaseConnectionException {
        flightManager.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws ModifyException, DataBaseConnectionException {
        flightManager.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws ModifyException, DataBaseConnectionException {
        flightManager.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws DataBaseConnectionException, ModifyException {
        return flightManager.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException, DataBaseConnectionException {
        flightManager.closeConnection();
    }
    //endregion
}
