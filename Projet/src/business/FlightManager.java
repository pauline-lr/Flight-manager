package business;

import dataAccess.*;
import exception.*;
import exception.dataBase.DataBaseAccessException;
import exception.dataBase.DataBaseCloseException;
import exception.dataBase.DataBaseConnectionException;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;
import pattern.DataAccessObjectPattern;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightManager {
    private DataAccessObjectPattern dataAccessObjectPattern;

    public FlightManager(){
        setDao(new AirlineDataBaseAccess());
    }

    private void setDao(DataAccessObjectPattern dataAccessObjectPattern) {
        this.dataAccessObjectPattern = dataAccessObjectPattern;
    }

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllPassengersOfAClass(className);
    }
    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return dataAccessObjectPattern.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return dataAccessObjectPattern.getAllFlights();
    }
    public String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllFlightsForComboBox();
    }
    public String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllPilotsForComboBox();
    }
    public String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllPlanesForComboBox();
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllClassesForComboBox();
    }
    public String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllAirportsForComboBox();
    }
    public String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllTerminalsOfAnAirportForComboBox(airportId);
    }
    public String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllGatesOfAnAirportAndTerminalForComboBox(airportId, terminalId);
    }
    public String getPilotToString(String pilotId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getPilotToString(pilotId);
    }
    public String getPlaneToString(Integer planeId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getPlaneToString(planeId);
    }
    public String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAirportToString(gateId);
    }
    public String getTerminalToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getTerminalToString(gateId);
    }
    public String getGateToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObjectPattern.getGateToString(gateId);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.modifyFlight(flightToUpdate, originalNumber);
    }
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.modifyFlight(flightToUpdate);
    }
    public void deleteFlight(String flightToDelete)
            throws SQLException, DataBaseConnectionException {
        dataAccessObjectPattern.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        dataAccessObjectPattern.closeConnection();
    }
    //endregion

    // tâche métier
}
