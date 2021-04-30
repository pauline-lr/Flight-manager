package dataAccess;

import model.Flight;
import model.Pilot;
import model.Plane;

import java.sql.*;
import java.util.*;

// Import customized exceptions

public class AirlineDBAccess {
    //region Search functions
    public static ArrayList<Flight> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate) throws SQLException {
        ArrayList<Flight> flights;
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());

        Connection connection = SingletonConnection.getInstance();

        String sql = "SELECT * FROM flight WHERE departure_time BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, startDateSQL);
        statement.setDate(2, endDateSQL);
        ResultSet data = statement.executeQuery();

        flights = flightResultSetIntoArrayList(data);

        connection.close();
        return flights;
    }
    //endregion

    //region List functions
    public static ArrayList<Flight> getAllFlights() throws SQLException {
        ArrayList<Flight> flights;

        Connection connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight");

        flights = flightResultSetIntoArrayList(data);

        connection.close();
        return flights;
    }
    public static ArrayList<Pilot> getAllPilots() throws SQLException {
        ArrayList<Pilot> pilots;

        Connection connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot");

        pilots = pilotResultSetIntoArrayList(data);

        connection.close();
        return pilots;
    }
    public static ArrayList<Plane> getAllPlanes() throws SQLException {
        ArrayList<Plane> planes;

        Connection connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM plane");

        planes = planeResultSetIntoArrayList(data);

        connection.close();
        return planes;
    }
    //endregion

    //region Manage functions
    public static void deleteFlight(Flight flightToDelete) throws SQLException {
        Connection connection = SingletonConnection.getInstance();

        String sql = "DELETE FROM flight WHERE number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, flightToDelete.getNumber());
        statement.executeUpdate();

        connection.close();
    }
    public static void modifyFlight(){

    }
    //endregion

    //region Tool functions
    private static ArrayList<Flight> flightResultSetIntoArrayList(ResultSet data) throws SQLException {
        ArrayList<Flight> flights = new ArrayList<>();
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

        return flights;
    }
    private static ArrayList<Pilot> pilotResultSetIntoArrayList(ResultSet data) throws SQLException {
        ArrayList<Pilot> pilots = new ArrayList<>();
        Pilot pilot;
        GregorianCalendar flyingTime = new GregorianCalendar();

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

        return pilots;
    }
    private static ArrayList<Plane> planeResultSetIntoArrayList(ResultSet data) throws SQLException {
        ArrayList<Plane> planes = new ArrayList<>();
        Plane plane;

        while (data.next()) {
            plane = new Plane(
                    data.getString("model"),
                    data.getString("brand")
            );

            planes.add(plane);
        }
        return planes;
    }
    //endregion
}
