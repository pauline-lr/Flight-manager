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
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException, AllDataException;

    ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException, AllDataException;
    //endregion

    //region Get to String
    String getFlightToString(String flightNumber)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String getPilotToString(String pilotLicenceNumber)
            throws SQLException, DataBaseConnectionException, AllPilotsException, AllDataException;

    String getPlaneToString(Integer planeID)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String getAirportToString(String gateID)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String getTerminalToString(String gateID)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String getGateToString(String gateID)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllFlightsToString()
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllPilotsToString()
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllPlanesToString()
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllClassesToString()
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllAirportsToString()
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws SQLException, DataBaseConnectionException, AllDataException;

    String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws SQLException, DataBaseConnectionException, AllDataException;
    //endregion

    //region Search
    ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException, AllDataException, DataBaseConnectionException;

    ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException, AllDataException, DataBaseConnectionException;

    ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException, AllDataException, DataBaseConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flight)
            throws SQLException, DataBaseConnectionException, AddDataException, AllDataException;

    void modifyFlight(Flight flight, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException, ModifyException;

    void deleteFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException, ModifyException, AllDataException;
    //endregion

    //region Test
    Boolean flightNumberIsExisting(String flightNumber)
            throws SQLException, DataBaseConnectionException, ModifyException;
    //endregion

    //region Connection
    void closeConnection()
            throws DataBaseCloseException, DataBaseConnectionException;
    //endregion
}
