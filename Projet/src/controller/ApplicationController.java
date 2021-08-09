package controller;

import business.FlightManager;
import exception.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;

import java.util.*;

public class ApplicationController {
    private FlightManager flightManager;

    public ApplicationController() {
        setFlightManager(new FlightManager());
    }

    private void setFlightManager(FlightManager flightManager) {
        this.flightManager = flightManager;
    }

    public ArrayList<String> getPilotsInOrder(GregorianCalendar date, String airportID)
            throws ConnectionException, RetrievalException {
        return flightManager.getPilotsInOrder(flightManager.getOnLocationPilots(date, airportID), flightManager.getAllPilots(date));
    }

    //region Get
    public Flight getFlight(String flightNumber)
            throws RetrievalException, ConnectionException, FlightException.NumberFlightException {
        return flightManager.getFlight(flightNumber);
    }

    public ArrayList<Flight> getAllFlights()
            throws RetrievalException, ConnectionException, FlightException.NumberFlightException {
        return flightManager.getAllFlights();
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws RetrievalException, ConnectionException {
        return flightManager.getFlightToString(flightNumber);
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws RetrievalException, ConnectionException {
        return flightManager.getPilotToString(pilotLicenceNumber);
    }

    public String getPlaneToString(Integer planeID)
            throws RetrievalException, ConnectionException {
        return flightManager.getPlaneToString(planeID);
    }

    public String getAirportToString(String gateID)
            throws RetrievalException, ConnectionException {
        return flightManager.getAirportToString(gateID);
    }

    public String getTerminalToString(String gateID)
            throws RetrievalException, ConnectionException {
        return flightManager.getTerminalToString(gateID);
    }

    public String getGateToString(String gateID)
            throws RetrievalException, ConnectionException {
        return flightManager.getGateToString(gateID);
    }

    public ArrayList<String> getAllFlightsToString()
            throws RetrievalException, ConnectionException {
        return flightManager.getAllFlightsToString();
    }

    public ArrayList<String> getAllPilotsToString()
            throws RetrievalException, ConnectionException {
        return flightManager.getAllPilotsToString();
    }

    public ArrayList<String> getAllAvailablePilotsToString(GregorianCalendar date)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllAvailablePilotsToString(date);
    }

    public ArrayList<String> getAllAvailablePlanesToString(GregorianCalendar date)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllAvailablePlanesToString(date);
    }

    public ArrayList<String> getAllClassesToString()
            throws RetrievalException, ConnectionException {
        return flightManager.getAllClassesToString();
    }

    public ArrayList<String> getAllAirportsToString()
            throws RetrievalException, ConnectionException {
        return flightManager.getAllAirportsToString();
    }

    public ArrayList<String> getAllTerminalsOfAnAirportToString(String airportCode)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllTerminalsOfAnAirportToString(airportCode);
    }

    public ArrayList<String> getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllGatesOfAnAirportAndTerminalToString(airportCode, terminal);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllFlightsBetweenDates(startDate, endDate);
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllPassengersOfAClass(className);
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws RetrievalException, ConnectionException {
        return flightManager.getAllFlightsOfAPilot(pilotLicenceNumber);
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws AddException, ConnectionException {
        flightManager.addFlight(flight);
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws ModifyException, ConnectionException {
        flightManager.modifyFlight(flight, originalFlightNumber);
    }

    public void deleteFlight(String flightNumber)
            throws ModifyException, ConnectionException {
        flightManager.deleteFlight(flightNumber);
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws ConnectionException, ModifyException {
        return flightManager.flightNumberIsExisting(flightNumber);
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws CloseException, ConnectionException {
        flightManager.closeConnection();
    }
    //endregion
}
