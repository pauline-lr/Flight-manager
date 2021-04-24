package dataAccess;

import exception.AllFlightsException;
import exception.AllPilotsException;
import model.Flight;
import model.Pilot;

import java.sql.*;
import java.util.*;

public class AirlineDBAccess {
    public static ArrayList<Flight> getAllFlights() throws AllFlightsException, SQLException {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        Connection connection = SingletonConnection.getInstance();
        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight");
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        Flight flight;

        while (data.next()) {
            departureTime.setTime(data.getDate("departure_time"));
            arrivalTime.setTime(data.getDate("arrival_time"));
            //flight = new Flight(data.getString("first_name"), data.getString("last_name"),
            //        data.getString("phone_number"), data.getString("email_address"),
            //        data.getString("licence_number"));
            //flights.add(flight);
        }

        connection.close();
        return flights;
    }


    public static ArrayList<Pilot> getAllPilots() throws AllPilotsException, SQLException {
        ArrayList<Pilot> pilots = new ArrayList<Pilot>();

        Connection connection = SingletonConnection.getInstance();
        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot");
        GregorianCalendar flyingTime = new GregorianCalendar();
        Pilot pilot;

        while (data.next()) {
            System.out.println(data.getDate("flying_time"));
            flyingTime.setTime(data.getDate("flying_time"));
            pilot = new Pilot(data.getString("first_name"), data.getString("last_name"),
                    data.getString("phone_number"), data.getString("email_address"),
                    data.getString("licence_number"), flyingTime);
            pilots.add(pilot);
        }

        connection.close();
        return pilots;
    }
}
