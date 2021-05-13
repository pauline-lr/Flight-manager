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
            throws SQLException, DataAccessException;
    ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws SQLException;
    ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException;
    //endregion

    //region Get
    ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DBConnectionException;
    ArrayList<Pilot> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DBConnectionException;
    ArrayList<String> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DBConnectionException;
    ArrayList<Plane> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DBConnectionException;
    ArrayList<model.Class> getAllClasses() throws SQLException, NameClassException, DBConnectionException;
    //endregion

    //region Edit
    void addFlight(Flight flightToAdd) throws SQLException, DBConnectionException;
    void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DBConnectionException;
    void deleteFlight(Flight flightToDelete) throws SQLException, DBConnectionException;
    //endregion

    //region Connection
    void closeConnection() throws DBCloseException;
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
    PreparedStatement preparedFlightStatement(String sql, Flight flight) throws SQLException, DBConnectionException;
    */
    //endregion
}
