package dataAccess;

import exception.AllFlightsException;
import exception.AllPilotsException;
import model.*;

import java.sql.*;
import java.util.*;

public class AirlineDBAccess {
    public static ArrayList<Flight> getAllFlights() throws AllFlightsException, SQLException {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        Connection connection = SingletonConnection.getInstance();
        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight");
        Flight flight;
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        String mealDescription;
        Pilot pilot;
        GregorianCalendar flyingTime = new GregorianCalendar();
        Gate departureGate;
        Airport departureAirport;
        Gate arrivalGate;
        Airport arrivalAirport;
        Plane plane;

        while (data.next()) {
            departureTime.setTime(data.getDate("departure_time"));
            arrivalTime.setTime(data.getDate("arrival_time"));
            flyingTime.setTime(data.getDate("flying_time"));

            flight = new Flight(
                    data.getString("number"),
                    departureTime,
                    arrivalTime,
                    data.getBoolean("is_meal_on_board"),
                    data.getObject("pilot", pilot = new Pilot(
                            data.getString("first_name"),
                            data.getString("last_name"),
                            data.getString("phone_number"),
                            data.getString("email_address"),
                            data.getString("licence_number"),
                            flyingTime
                        )
                    ),
                    data.getObject("departure_gate", departureGate = new Gate(
                            data.getString("terminal"),
                            data.getInt("number"),
                            data.getObject("airport", departureAirport = new Airport(
                                    data.getString("code"),
                                    data.getString("name"),
                                    data.getString("country")
                                )
                            )
                        )
                    ),
                    data.getObject("arrival_gate", arrivalGate = new Gate(
                            data.getString("terminal"),
                            data.getInt("number"),
                            data.getObject("airport", arrivalAirport = new Airport(
                                    data.getString("code"),
                                    data.getString("name"),
                                    data.getString("country")
                                )
                            )
                        )
                    ),
                    data.getObject("plane", plane = new Plane (
                            data.getString("model"),
                            data.getInt("number"),
                            data.getString("brand")
                        )
                    )
            );

            mealDescription = data.getString("meal_description");
            if (!data.wasNull()) {
                flight.setMealDescription(mealDescription);
            }

            flights.add(flight);
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
            flyingTime.setTime(data.getTime("flying_time"));
            pilot = new Pilot(
                    data.getString("first_name"),
                    data.getString("last_name"),
                    data.getString("phone_number"),
                    data.getString("email_address"),
                    data.getString("licence_number"),
                    flyingTime
            );
            pilots.add(pilot);
        }

        connection.close();
        return pilots;
    }


}
