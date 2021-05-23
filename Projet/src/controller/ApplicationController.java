package controller;

import business.*;
import exception.*;
import exception.dataBase.DataBaseAccessException;
import exception.dataBase.DataBaseCloseException;
import exception.dataBase.DataBaseConnectionException;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

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
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return flightManager.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return flightManager.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Get
    public Flight getFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getFlight(flightNumber);
    }

    public String getPilotToString(String pilotId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getPilotToString(pilotId);
    }

    public String getPlaneToString(Integer planeId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getPlaneToString(planeId);
    }

    public String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAirportToString(gateId);
    }

    public String getTerminalToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getTerminalToString(gateId);
    }

    public String getGateToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getGateToString(gateId);
    }

    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return flightManager.getAllFlights();
    }

    public String[] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllFlightsForComboBox();
    }

    public String[] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPilotsForComboBox();
    }

    public String[] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllPlanesForComboBox();
    }

    public String[] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllClassesForComboBox();
    }

    public String[] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllAirportsForComboBox();
    }

    public String[] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        return flightManager.getAllTerminalsOfAnAirportForComboBox(airportId);
    }

    public String[] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
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

    public void deleteFlight(String flightToDelete)
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
