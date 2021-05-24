package pattern;

import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.*;

import java.sql.*;
import java.util.*;

public interface DataAccessObjectPattern {
    //region Get
    Flight getFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException;

    ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException;
    //endregion

    //region Get to String
    String getPilotToString(String pilotLicenceNumber)
            throws SQLException, DataBaseConnectionException;

    String getPlaneToString(Integer planeID)
            throws SQLException, DataBaseConnectionException;

    String getAirportToString(String gateID)
            throws SQLException, DataBaseConnectionException;

    String getTerminalToString(String gateID)
            throws SQLException, DataBaseConnectionException;

    String getGateToString(String gateID)
            throws SQLException, DataBaseConnectionException;

    String[] getAllFlightsToString()
            throws SQLException, DataBaseConnectionException;

    String[] getAllPilotsToString()
            throws SQLException, DataBaseConnectionException;

    String[] getAllPlanesToString()
            throws SQLException, DataBaseConnectionException;

    String[] getAllClassesToString()
            throws SQLException, DataBaseConnectionException;

    String[] getAllAirportsToString()
            throws SQLException, DataBaseConnectionException;

    String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws SQLException, DataBaseConnectionException;

    String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Search
    ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException;

    ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException;

    ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException;
    //endregion

    //region Edit
    void addFlight(Flight flight)
            throws SQLException, DataBaseConnectionException;

    void modifyFlight(Flight flight, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException;

    void modifyFlight(Flight flight)
            throws SQLException, DataBaseConnectionException;

    void deleteFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Connection
    void closeConnection()
            throws DataBaseCloseException;
    //endregion
}
