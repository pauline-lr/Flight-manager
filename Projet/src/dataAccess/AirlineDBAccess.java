package dataAccess;

import exception.FlightException;
import model.Flight;

import java.sql.*;
import java.util.*;

public class AirlineDBAccess {
    public static ArrayList<Flight> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate) throws FlightException, SQLException {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis( ));
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis( ));

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


}
