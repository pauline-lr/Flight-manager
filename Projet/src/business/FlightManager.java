package business;

import dataAccess.*;
import exception.*;
import model.*;
import pattern.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightManager {
    private DAO dao;

    public FlightManager(){
        setDao(new AirlineDataBaseAccess());
    }

    private void setDao(DAO dao) {
        this.dao = dao;
    }

    //region Search
    public ArrayList<SearchFlightsBetweenDates> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return dao.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return dao.getAllPassengersOfAClass(className);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return dao.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return dao.getAllFlights();
    }
    public String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllFlightsForComboBox();
    }
    public String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllPilotsForComboBox();
    }
    public String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllPlanesForComboBox();
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllClassesForComboBox();
    }
    public String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllAirportsForComboBox();
    }
    public String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        return dao.getAllTerminalsOfAnAirportForComboBox(airportId);
    }
    public String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException {
        return dao.getAllGatesOfAnAirportAndTerminalForComboBox(airportId, terminalId);
    }
    public String getPilotToString(String pilotId)
            throws SQLException, DataBaseConnectionException {
        return dao.getPilotToString(pilotId);
    }
    public String getPlaneToString(Integer planeId)
            throws SQLException, DataBaseConnectionException {
        return dao.getPlaneToString(planeId);
    }
    public String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dao.getAirportToString(gateId);
    }
    public String getGateToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dao.getGateToString(gateId);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException {
        dao.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException {
        dao.modifyFlight(flightToUpdate, originalNumber);
    }
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException {
        dao.modifyFlight(flightToUpdate);
    }
    public void deleteFlight(String flightToDelete)
            throws SQLException, DataBaseConnectionException {
        dao.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        dao.closeConnection();
    }
    //endregion

    // tâche métier
}
