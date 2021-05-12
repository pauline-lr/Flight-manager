package business;

import dataAccess.AirlineDBAccess;
import pattern.DAO;

import java.util.ArrayList;

public class FlightManager {
    private DAO dao;

    public FlightManager(){
        setDao((DAO) new AirlineDBAccess());
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    public void closeConnection() {
        dao.closeConnection();
    }

    // obtentions
    /*public ArrayList<Flight> getAllFlight( ) throws FlightException {
        return dao.getAllFlight();
    }*/

    // ajouts

    // modif

    // suppressions

    // tâche métier
}
