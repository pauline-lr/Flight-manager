package controller;

import business.FlightManager;
import dataAccess.AirlineDBAccess;
import exception.DBCloseException;
import exception.FlightException;
import model.Flight;
import pattern.DAO;

import java.util.ArrayList;

public class ApplicationController {
    private FlightManager flightManager;

    public ApplicationController() {
        flightManager = new FlightManager();
    }



    public void closeConnection() throws DBCloseException {
        flightManager.closeConnection();
    }


    public ArrayList<Flight> getAllFlight() throws FlightException {
        return null;
    }
}
