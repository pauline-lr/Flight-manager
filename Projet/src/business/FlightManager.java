package business;

import dataAccess.*;
import exception.*;
import model.*;
import pattern.*;

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
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        return dao.getAllFlightsBetweenDates(startDate,endDate);
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(model.Class seatClass)
            throws DataBaseAccessException {
        return dao.getAllPassengersOfAClass(seatClass);
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws DataBaseAccessException {
        return dao.getAllFlightsOfAPilot(pilot);
    }
    //endregion

    //region Get
    public ArrayList<String> getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllFlightsForComboBox();
    }
    public ArrayList<String> getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllPilotsForComboBox();
    }
    public ArrayList<String> getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllAirportsForComboBox();
    }
    public ArrayList<Integer> getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllPlanesForComboBox();
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        return dao.getAllClassesForComboBox();
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
    public void deleteFlight(Flight flightToDelete)
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
