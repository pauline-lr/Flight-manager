package business;

import dataAccess.AirlineDBAccess;
import exception.*;
import model.*;
import pattern.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightManager {
    private DAO dao;

    public FlightManager(){
        setDao(new AirlineDBAccess());
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void closeConnection() throws DBCloseException {
        dao.closeConnection();
    }

    //region Search
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws SQLException, DataAccessException {
        return dao.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws SQLException {
        return dao.getAllPassengersOfAClass(seatClass);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException {
        return dao.getAllFlightsOfAPilot(pilot);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DBConnectionException {
        return dao.getAllFlights();
    }
    public ArrayList<Pilot> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DBConnectionException {
        return dao.getAllPilots();
    }
    public ArrayList<Airport> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DBConnectionException {
        return dao.getAllAirports();
    }
    public ArrayList<Plane> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DBConnectionException {
        return dao.getAllPlanes();
    }
    public ArrayList<model.Class> getAllClasses() throws SQLException, NameClassException, DBConnectionException {
        return dao.getAllClasses();
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd) throws SQLException, DBConnectionException {
        dao.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DBConnectionException {
        dao.modifyFlight(flightToUpdate, originalNumber);
    }
    public void deleteFlight(Flight flightToDelete) throws SQLException, DBConnectionException {
        dao.deleteFlight(flightToDelete);
    }
    //endregion

    // tâche métier
}
