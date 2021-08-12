package business;

import dataAccess.*;
import exception.*;
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

    public ArrayList<String> getOnLocationPilots(GregorianCalendar date, String airportID) throws ConnectionException, RetrievalException {
        ArrayList<String> onLocationPilots = getLastPilotFlightArrivingAt(date, airportID);
        return onLocationPilots;
    }

    public ArrayList<String> getAllPilots(GregorianCalendar date) throws ConnectionException, RetrievalException {
        ArrayList<String> allPilots = getAllAvailablePilotsToString(date);
        return allPilots;
    }

   public ArrayList<String> getPilotsInOrder(ArrayList<String> onLocationPilots, ArrayList<String> allPilots)  {
       ArrayList<String> newCollection = new ArrayList<>(onLocationPilots);

       for (String pilot: onLocationPilots) {
           allPilots.removeIf(pilot::equals);
       }

       newCollection.addAll(allPilots);

       return newCollection;
   }

    //region Get
    public Flight getFlight(String flightNumber)
            throws ConnectionException, FlightException.NumberFlightException, RetrievalException, FlightException.DepartureDateException, FlightException.ArrivalDateException {
        return dataAccessObjectPattern.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws ConnectionException, RetrievalException, FlightException.DepartureDateException, FlightException.ArrivalDateException {
        return dataAccessObjectPattern.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getGateToString(gateID);
    }

    public ArrayList<String> getAllFlightsToString()
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllFlightsToString();
    }

    public ArrayList<String> getAllPilotsToString()
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllPilotsToString();
    }

    public ArrayList<String> getLastPilotFlightArrivingAt(GregorianCalendar date, String airportID)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getLastPilotFlightArrivingAt(date, airportID);
    }

    public ArrayList<String> getAllAvailablePilotsToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllAvailablePilotsToString(date);
    }

    public ArrayList<String> getAllAvailablePlanesToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllAvailablePlanesToString(date);
    }

    public ArrayList<String> getAllClassesToString()
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllClassesToString();
    }

    public ArrayList<String> getAllAirportsToString()
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllAirportsToString();
    }

    public ArrayList<String> getAllTerminalsOfAnAirportToString(String airportCode)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public ArrayList<String> getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws ConnectionException, RetrievalException {
        return dataAccessObjectPattern.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws RetrievalException, ConnectionException {
        return dataAccessObjectPattern.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws RetrievalException, ConnectionException {
        return dataAccessObjectPattern.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws RetrievalException, ConnectionException {
        return dataAccessObjectPattern.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws AddException, ConnectionException {
        dataAccessObjectPattern.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws ModifyException, ConnectionException {
        dataAccessObjectPattern.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws ConnectionException, ModifyException {
        dataAccessObjectPattern.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws ConnectionException, ModifyException {
        return dataAccessObjectPattern.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws CloseException, ConnectionException {
        dataAccessObjectPattern.closeConnection();
    }
    //endregion
}
