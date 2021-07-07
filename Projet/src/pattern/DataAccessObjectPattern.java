package pattern;

import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

import java.util.*;

public interface DataAccessObjectPattern {
    //region Get
    Flight getFlight(String flightNumber)
            throws DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException, AllDataException;

    ArrayList<Flight> getAllFlights()
            throws DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException, AllDataException;
    //endregion

    //region Get to String
    String getFlightToString(String flightNumber)
            throws  DataBaseConnectionException, AllDataException;

    String getPilotToString(String pilotLicenceNumber)
            throws DataBaseConnectionException, AllDataException;

    String getPlaneToString(Integer planeID)
            throws DataBaseConnectionException, AllDataException;

    String getAirportToString(String gateID)
            throws DataBaseConnectionException, AllDataException;

    String getTerminalToString(String gateID)
            throws DataBaseConnectionException, AllDataException;

    String getGateToString(String gateID)
            throws DataBaseConnectionException, AllDataException;

    String[] getAllFlightsToString()
            throws DataBaseConnectionException, AllDataException;

    String[] getAllPilotsToString()
            throws DataBaseConnectionException, AllDataException;

    String[] getAllPlanesAvailableToString()
            throws DataBaseConnectionException, AllDataException;

    String[] getAllClassesToString()
            throws DataBaseConnectionException, AllDataException;

    String[] getAllAirportsToString()
            throws DataBaseConnectionException, AllDataException;

    String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws DataBaseConnectionException, AllDataException;

    String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws DataBaseConnectionException, AllDataException;
    //endregion

    //region Search
    ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws AllDataException, DataBaseConnectionException;

    ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws AllDataException, DataBaseConnectionException;

    ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws AllDataException, DataBaseConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flight)
            throws DataBaseConnectionException, AddDataException, AllDataException;

    void modifyFlight(Flight flight, String originalFlightNumber)
            throws DataBaseConnectionException, ModifyException;

    void deleteFlight(String flightNumber)
            throws  DataBaseConnectionException, ModifyException, AllDataException;
    //endregion

    //region Test
    Boolean flightNumberIsExisting(String flightNumber)
            throws  DataBaseConnectionException, ModifyException;
    //endregion

    //region Connection
    void closeConnection()
            throws DataBaseCloseException, DataBaseConnectionException;
    //endregion
}
