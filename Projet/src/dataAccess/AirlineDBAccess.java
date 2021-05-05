package dataAccess;

import model.*;

import java.sql.*;

//             !!!                     Import customized exceptions

public class AirlineDBAccess {
    private static Connection connection;

    public static void addFlight(EditFlight flightToAdd) throws SQLException {
        connection = SingletonConnection.getInstance();

        String departureGateId = addGate(flightToAdd.getDepartureGateTerminal(), flightToAdd.getDepartureGateNumber(), flightToAdd.getDepartureAirportCode());
        String arrivalGateId = addGate(flightToAdd.getArrivalGateTerminal(), flightToAdd.getArrivalGateNumber(), flightToAdd.getArrivalAirportCode());

        String sql =
            "INSERT INTO flight " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,  flightToAdd.getNumber());
        preparedStatement.setDate(2, new java.sql.Date(flightToAdd.getDepartureTime().getTimeInMillis()));
        preparedStatement.setDate(3, new java.sql.Date(flightToAdd.getArrivalTime().getTimeInMillis()));
        preparedStatement.setBoolean(4, flightToAdd.getMealOnBoard());
        if (flightToAdd.getMealDescription() == null) {
            preparedStatement.setNull(5, Types.VARCHAR);
        } else {
            preparedStatement.setString(5, flightToAdd.getMealDescription());
        }
        preparedStatement.setString(6, departureGateId);
        preparedStatement.setString(7, arrivalGateId);
        preparedStatement.setString(8, flightToAdd.getPilot());
        preparedStatement.setInt(9, flightToAdd.getPlane());

        preparedStatement.executeUpdate();

        connection.close();
    }

    private static String addGate(String gateTerminal, Integer gateNumber, String airport) throws SQLException {
        connection = SingletonConnection.getInstance();

        String sql =
                "INSERT INTO gate " +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        String gateId = String.valueOf(gateTerminal) + gateNumber;

        preparedStatement.setString(1,  gateId);
        preparedStatement.setString(2, gateTerminal);
        preparedStatement.setInt(3,  gateNumber);
        preparedStatement.setString(4,  airport);

        preparedStatement.executeUpdate();

        connection.close();

        return gateId;
    }

    

/*
    //region Search functions
    public static ArrayList<Flight> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate) throws SQLException {
        ArrayList<Flight> flights;
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());

        connection = SingletonConnection.getInstance();

        String sql =
                "SELECT * FROM flight " +
                "WHERE departure_time BETWEEN ? AND ? " +
                "ORDER BY departure_time";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, startDateSQL);
        preparedStatement.setDate(2, endDateSQL);

        ResultSet data = preparedStatement.executeQuery();

        flights = flightResultSetIntoArrayList(data);

        connection.close();
        return flights;
    }
    //endregion

    //region List functions
    public static ArrayList<Flight> getAllFlights() throws SQLException {
        ArrayList<Flight> flights;

        connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight ORDER BY departure_time");

        flights = flightResultSetIntoArrayList(data);

        connection.close();
        return flights;
    }
    public static ArrayList<Pilot> getAllPilots() throws SQLException {
        ArrayList<Pilot> pilots;

        connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot ORDER BY first_name, last_name");

        pilots = pilotResultSetIntoArrayList(data);

        connection.close();
        return pilots;
    }
    public static ArrayList<Airport> getAllAirports() throws SQLException {
        ArrayList<Airport> airports;

        connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM airport ORDER BY name");

        airports = airportResultSetIntoArrayList(data);

        connection.close();
        return airports;
    }
    public static ArrayList<Plane> getAllPlanes() throws SQLException {
        ArrayList<Plane> planes;

        connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM plane ORDER BY model, brand");

        planes = planeResultSetIntoArrayList(data);

        connection.close();
        return planes;
    }
    public static ArrayList<Class> getAllClasses() throws SQLException {
        ArrayList<Class> classes;

        connection = SingletonConnection.getInstance();

        Statement statement = connection.createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM class ORDER BY name");

        classes = classResultSetIntoArrayList(data);

        return classes;
    }
    //endregion

    //region Manage functions
    public static void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException {
        connection = SingletonConnection.getInstance();

        String sql =
                "UPDATE flight " +
                "SET number = ?, departure_time = ?, arrival_time = ?, is_meal_on_board = ?, " +
                "meal_description = ?, departure_gate = ?, arrival_gate = ?, pilot = ?, plane = ? " +
                "WHERE number = ?";
        PreparedStatement preparedStatement = preparedFlightStatement(connection, sql, flightToUpdate);
        preparedStatement.setString(10, originalNumber);
        preparedStatement.executeUpdate();

        connection.close();
    }
    public static void deleteFlight(Flight flightToDelete) throws SQLException {
        connection = SingletonConnection.getInstance();

        String sql =
                "DELETE FROM flight " +
                        "WHERE number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, flightToDelete.getNumber());
        preparedStatement.executeUpdate();

        connection.close();
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
    private static ArrayList<Airport> airportResultSetIntoArrayList(ResultSet data) throws SQLException {
        ArrayList<Airport> airports = new ArrayList<>();
        Airport airport;

        while (data.next()) {
            airport = new Airport(
                    data.getString("code"),
                    data.getString("name"),
                    data.getString("country")
            );

            airports.add(airport);
        }

        return airports;
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
    private static ArrayList<Class> classResultSetIntoArrayList(ResultSet data) throws SQLException {
        ArrayList<Class> classes = new ArrayList<>();
        Class classe;

        while (data.next()) {
            classe = new Class(
                    data.getString("name")
            );

            classes.add(classe);
        }

        return classes;
    }
    //endregion
 */
}
