package controller;

import business.FlightManager;
import dataAccess.SingletonConnection;
import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.*;

import java.sql.*;
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
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getGateToString(gateID);
    }

    public String[] getAllFlightsToString()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllFlightsToString();
    }

    public String[] getAllPilotsToString()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPilotsToString();
    }

    public String[] getAllPlanesToString()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPlanesToString();
    }

    public String[] getAllClassesToString()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllClassesToString();
    }

    public String[] getAllAirportsToString()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllAirportsToString();
    }

    public String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return flightManager.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws SQLException, DataBaseConnectionException {
        flightManager.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException {
        flightManager.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        flightManager.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        return flightManager.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        flightManager.closeConnection();
    }
    //endregion
}
