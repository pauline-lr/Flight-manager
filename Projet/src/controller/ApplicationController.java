package controller;

import business.*;
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

    private void setFlightManager(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    //region Search
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws DataBaseAccessException {
        return flightManager.getAllPassengersOfAClass(seatClass);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsOfAPilot(pilot);
    }
    //endregion

    //region Get
    public ArrayList<String> getAllFlightsNumber()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllFlightsNumber();
    }
    public ArrayList<String> getAllPilotsLicenceNumber()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPilotsLicenceNumber();
    }
    public ArrayList<String> getAllAirportsCode()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllAirportsCode();
    }
    public ArrayList<Integer> getAllPlanesID()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPlanesID();
    }
    public ArrayList<String> getAllClassesName()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllClassesName();
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException {
        flightManager.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException {
        flightManager.modifyFlight(flightToUpdate, originalNumber);
    }
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException {
        flightManager.modifyFlight(flightToUpdate);
    }
    public void deleteFlight(Flight flightToDelete)
            throws SQLException, DataBaseConnectionException {
        flightManager.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        flightManager.closeConnection();
    }
    //endregion
}
