package controller;

import business.FlightManager;
import exception.*;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ApplicationController {
    private FlightManager flightManager;

    public ApplicationController() {
        setFlightManager(new FlightManager());
    }

    public void setFlightManager(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    //region Search
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws SQLException, DataAccessException {
        return flightManager.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws SQLException {
        return flightManager.getAllPassengersOfAClass(seatClass);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException {
        return flightManager.getAllFlightsOfAPilot(pilot);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DBConnectionException {
        return flightManager.getAllFlights();
    }
    public ArrayList<String> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DBConnectionException {
        return flightManager.getAllPilots();
    }
    public ArrayList<String> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DBConnectionException {
        return flightManager.getAllAirports();
    }
    public ArrayList<String> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DBConnectionException {
        return flightManager.getAllPlanes();
    }
    public ArrayList<model.Class> getAllClasses() throws SQLException, NameClassException, DBConnectionException {
        return flightManager.getAllClasses();
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd) throws SQLException, DBConnectionException {
        flightManager.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DBConnectionException {
        flightManager.modifyFlight(flightToUpdate, originalNumber);
    }
    public void deleteFlight(Flight flightToDelete) throws SQLException, DBConnectionException {
        flightManager.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection() throws DBCloseException {
        flightManager.closeConnection();
    }
    //endregion
}
