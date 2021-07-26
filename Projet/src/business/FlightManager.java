package business;

import dataAccess.*;
import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

import java.util.*;

public class FlightManager {
    private AirlineDataBaseAccess dataAccessObjectPattern;

    public FlightManager() {
        setDataAccessObjectPattern(new AirlineDataBaseAccess());
    }

    private void setDataAccessObjectPattern(AirlineDataBaseAccess dataAccessObjectPattern) {
        this.dataAccessObjectPattern = dataAccessObjectPattern;
    }

    public ArrayList<String> getPilotsInOrder(GregorianCalendar date, String airportID) throws DataBaseConnectionException, AllDataException {
        ArrayList<String> onLocationPilots = getLastPilotFlightArrivingAt(date, airportID);
        ArrayList<String> allPilots = getAllAvailablePilotsToString(date);
        ArrayList<String> newCollection = new ArrayList<>(onLocationPilots);

        for (String pilot: onLocationPilots) {
            allPilots.removeIf(pilot::equals);
        }

        newCollection.addAll(allPilots);

        return newCollection;
    }

    //region Get
    public Flight getFlight(String flightNumber)
            throws DataBaseConnectionException, FlightException.NumberFlightException, AllDataException {
        return dataAccessObjectPattern.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getGateToString(gateID);
    }

    public ArrayList<String> getAllFlightsToString()
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllFlightsToString();
    }

    public ArrayList<String> getAllPilotsToString()
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllPilotsToString();
    }

    public ArrayList<String> getLastPilotFlightArrivingAt(GregorianCalendar date, String airportID)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getLastPilotFlightArrivingAt(date, airportID);
    }

    public ArrayList<String> getAllAvailablePilotsToString(GregorianCalendar date)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllAvailablePilotsToString(date);
    }

    public ArrayList<String> getAllAvailablePlanesToString(GregorianCalendar date)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllAvailablePlanesToString(date);
    }

    public ArrayList<String> getAllClassesToString()
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllClassesToString();
    }

    public ArrayList<String> getAllAirportsToString()
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllAirportsToString();
    }

    public ArrayList<String> getAllTerminalsOfAnAirportToString(String airportCode)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public ArrayList<String> getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws  DataBaseConnectionException, AllDataException {
        return dataAccessObjectPattern.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws AllDataException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws AllDataException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws AllDataException, DataBaseConnectionException {
        return dataAccessObjectPattern.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws AddDataException,  DataBaseConnectionException {
        dataAccessObjectPattern.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws ModifyException, DataBaseConnectionException {
        dataAccessObjectPattern.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws DataBaseConnectionException, ModifyException {
        dataAccessObjectPattern.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws DataBaseConnectionException, ModifyException {
        return dataAccessObjectPattern.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException, DataBaseConnectionException {
        dataAccessObjectPattern.closeConnection();
    }
    //endregion
}
