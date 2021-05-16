package pattern;
// https://www.baeldung.com/java-dao-pattern
// domain class = Flight

import exception.*;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface DAO {
    //region Search
    ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException;
    ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws DataBaseAccessException;
    ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws DataBaseAccessException;
    //endregion

    //region Get
    ArrayList<String> getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException;
    ArrayList<String> getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException;
    ArrayList<String> getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException;
    ArrayList<Integer> getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException;
    String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException;
    void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException;
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException;
    void deleteFlight(Flight flightToDelete)
            throws SQLException, DataBaseConnectionException;
    //endregion

    //region Connection
    void closeConnection()
            throws DataBaseCloseException;
    //endregion

    //region Tools
    /*
    ArrayList<Flight> flightResultSetIntoArrayList(ResultSet data) throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException;
    ArrayList<Pilot> pilotResultSetIntoArrayList(ResultSet data)
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException, PersonException.LastNameException,
            PersonException.EmailException, PilotException.LicenceNumberException, PilotException.FlyingFlightException;
    ArrayList<Airport> airportResultSetIntoArrayList(ResultSet data)
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException;
    ArrayList<Plane> planeResultSetIntoArrayList(ResultSet data)
            throws SQLException, PlaneException.ModelException, PlaneException.BrandException;
    ArrayList<model.Class> classResultSetIntoArrayList(ResultSet data) throws SQLException, NameClassException;
    PreparedStatement preparedFlightStatement(String sql, Flight flight) throws SQLException, DataBaseConnectionException;
    */
    //endregion
}
