package pattern;

import exception.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DataAccessObject {
    //region Search
    ArrayList<SearchFlightsBetweenDates> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException;
    ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException;
    ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException;
    //endregion

    //region Get
    String getPilotToString(String pilotId)
            throws SQLException, DataBaseConnectionException;
    String getPlaneToString(Integer planeId)
            throws SQLException, DataBaseConnectionException;
    String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException;
    String getTerminalToString(String gateId)
            throws SQLException, DataBaseConnectionException;
    String getGateToString(String gateId)
            throws SQLException, DataBaseConnectionException;
    ArrayList<Flight> getAllFlights()
            throws DataBaseConnectionException, SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException;
    String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException;
    String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException;
    void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException;
    void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException;
    void deleteFlight(String flightToDelete)
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Connection
    void closeConnection()
            throws DataBaseCloseException;
    //endregion
}
