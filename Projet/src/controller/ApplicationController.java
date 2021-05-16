package controller;

import business.*;
import dataAccess.SingletonConnection;
import exception.*;
import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllFlightsForComboBox();
    }
    public String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPilotsForComboBox();
    }
    public String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPlanesForComboBox();
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllClassesForComboBox();
    }
    public String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllAirportsForComboBox();
    }
    public String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllTerminalsOfAnAirportForComboBox(airportId);
    }
    public String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllGatesOfAnAirportAndTerminalForComboBox(airportId, terminalId);
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
