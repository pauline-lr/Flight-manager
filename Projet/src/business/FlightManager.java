package business;

import dataAccess.AirlineDataBaseAccess;
import exception.*;
import model.*;
import pattern.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightManager {
    private DAO dao;

    public FlightManager(){
        setDao(new AirlineDataBaseAccess());
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    //region Search
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws SQLException, DataBaseAccessException {
        return dao.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws SQLException, DataBaseAccessException {
        return dao.getAllPassengersOfAClass(seatClass);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException, DataBaseAccessException {
        return dao.getAllFlightsOfAPilot(pilot);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DataBaseConnectionException {
        return dao.getAllFlights();
    }
    public ArrayList<String> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DataBaseConnectionException {
        return dao.getAllPilots();
    }
    public ArrayList<String> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DataBaseConnectionException {
        return dao.getAllAirports();
    }
    public ArrayList<String> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DataBaseConnectionException {
        return dao.getAllPlanes();
    }
    public ArrayList<model.Class> getAllClasses() throws SQLException, NameClassException, DataBaseConnectionException {
        return dao.getAllClasses();
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd) throws SQLException, DataBaseConnectionException {
        dao.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DataBaseConnectionException {
        dao.modifyFlight(flightToUpdate, originalNumber);
    }

    public void modifyFlight(Flight flightToUpdate) throws SQLException, DataBaseConnectionException {
        dao.modifyFlight(flightToUpdate);
    }

    public void deleteFlight(Flight flightToDelete) throws SQLException, DataBaseConnectionException {
        dao.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection() throws DataBaseCloseException {
        dao.closeConnection();
    }
    //endregion

    // tâche métier
}
