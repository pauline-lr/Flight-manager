package dataAccess;

import model.Flight;
import model.Pilot;

import java.sql.*;
import java.util.*;

// Importer customized exceptions

public class AirlineDBAccess {
    public static ArrayList<Flight> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate) throws SQLException {
        ArrayList<Flight> flights = new ArrayList<>();
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());

        Connection connection = SingletonConnection.getInstance();

        String sql = "SELECT * FROM flight WHERE departure_time BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, startDateSQL);
        statement.setDate(2, endDateSQL);
        ResultSet data = statement.executeQuery();

        Flight flight;
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        String mealDescription;
        while (data.next()) {
            departureTime.setTime(data.getDate("departure_time"));
            arrivalTime.setTime(data.getDate("arrival_time"));

            flight = new Flight(
                data.getString("number"),
                departureTime,
                arrivalTime,
                data.getBoolean("is_meal_on_board"),
                data.getString("pilot"),
                data.getString("departure_gate"),
                data.getString("arrival_gate"),
                data.getInt("plane")
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

    public static ArrayList<Pilot> getAllPilots() throws SQLException {
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
