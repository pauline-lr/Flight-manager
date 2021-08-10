package pattern;

import exception.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

import java.util.*;

public interface DataAccessObjectPattern {
    //region Get
    Flight getFlight(String flightNumber)
            throws ConnectionException, FlightException.NumberFlightException, RetrievalException, FlightException.DepartureDateException, FlightException.ArrivalDateException;

    ArrayList<Flight> getAllFlights()
            throws ConnectionException, FlightException.NumberFlightException, RetrievalException, FlightException.DepartureDateException, FlightException.ArrivalDateException;
    //endregion

    //region Get to String
    String getFlightToString(String flightNumber)
            throws ConnectionException, RetrievalException;

    String getPilotToString(String pilotLicenceNumber)
            throws ConnectionException, RetrievalException;

    String getPlaneToString(Integer planeID)
            throws ConnectionException, RetrievalException;

    String getAirportToString(String gateID)
            throws ConnectionException, RetrievalException;

    String getTerminalToString(String gateID)
            throws ConnectionException, RetrievalException;

    String getGateToString(String gateID)
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllFlightsToString()
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllPilotsToString()
            throws ConnectionException, RetrievalException;

    ArrayList<String> getLastPilotFlightArrivingAt(GregorianCalendar date, String airportID)
            throws RetrievalException, ConnectionException;

    ArrayList<String> getAllAvailablePilotsToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllAvailablePlanesToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllClassesToString()
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllAirportsToString()
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllTerminalsOfAnAirportToString(String airportCode)
            throws ConnectionException, RetrievalException;

    ArrayList<String> getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws ConnectionException, RetrievalException;
    //endregion

    //region Search
    ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws RetrievalException, ConnectionException;

    ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws RetrievalException, ConnectionException;

    ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws RetrievalException, ConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flight)
            throws ConnectionException, AddException, RetrievalException;

    void modifyFlight(Flight flight, String originalFlightNumber)
            throws ConnectionException, ModifyException;

    void deleteFlight(String flightNumber)
            throws ConnectionException, ModifyException, RetrievalException;
    //endregion

    //region Test
    Boolean flightNumberIsExisting(String flightNumber)
            throws ConnectionException, ModifyException;
    //endregion

    //region Connection
    void closeConnection()
            throws CloseException, ConnectionException;
    //endregion
}
