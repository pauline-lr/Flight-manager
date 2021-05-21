package business;

import dataAccess.*;
import exception.*;
import model.*;
import pattern.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightManager {
    private DataAccessObject dataAccessObject;

    public FlightManager(){
        setDao(new AirlineDataBaseAccess());
    }

    private void setDao(DataAccessObject dataAccessObject) {
        this.dataAccessObject = dataAccessObject;
    }

    //region Search
    public ArrayList<SearchFlightsBetweenDates> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return dataAccessObject.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        return dataAccessObject.getAllPassengersOfAClass(className);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        return dataAccessObject.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        return dataAccessObject.getAllFlights();
    }
    public String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllFlightsForComboBox();
    }
    public String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllPilotsForComboBox();
    }
    public String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllPlanesForComboBox();
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllClassesForComboBox();
    }
    public String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllAirportsForComboBox();
    }
    public String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllTerminalsOfAnAirportForComboBox(airportId);
    }
    public String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAllGatesOfAnAirportAndTerminalForComboBox(airportId, terminalId);
    }
    public String getPilotToString(String pilotId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getPilotToString(pilotId);
    }
    public String getPlaneToString(Integer planeId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getPlaneToString(planeId);
    }
    public String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getAirportToString(gateId);
    }
    public String getTerminalToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getTerminalToString(gateId);
    }
    public String getGateToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        return dataAccessObject.getGateToString(gateId);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException {
        dataAccessObject.addFlight(flightToAdd);
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException {
        dataAccessObject.modifyFlight(flightToUpdate, originalNumber);
    }
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException {
        dataAccessObject.modifyFlight(flightToUpdate);
    }
    public void deleteFlight(String flightToDelete)
            throws SQLException, DataBaseConnectionException {
        dataAccessObject.deleteFlight(flightToDelete);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        dataAccessObject.closeConnection();
    }
    //endregion

    // tâche métier
}
