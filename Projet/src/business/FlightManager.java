package business;

import dataAccess.*;
import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.*;
import pattern.DataAccessObjectPattern;

import java.sql.*;
import java.util.*;

public class FlightManager {
    private DataAccessObjectPattern dataAccessObjectPattern;

    public FlightManager() {
        setDataAccessObjectPattern(new AirlineDataBaseAccess());
    }

    private void setDataAccessObjectPattern(DataAccessObjectPattern dataAccessObjectPattern) {
        this.dataAccessObjectPattern = dataAccessObjectPattern;
    }

    //
    // tâche métier
    //

    //region Get
    public Flight getFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return dataAccessObjectPattern.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return dataAccessObjectPattern.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getGateToString(gateID);
    }

    public String[] getAllFlightsToString()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllFlightsToString();
    }

    public String[] getAllPilotsToString()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllPilotsToString();
    }

    public String[] getAllPlanesToString()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllPlanesToString();
    }

    public String[] getAllClassesToString()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllClassesToString();
    }

    public String[] getAllAirportsToString()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllAirportsToString();
    }

    public String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        dataAccessObjectPattern.closeConnection();
    }
    //endregion
}
