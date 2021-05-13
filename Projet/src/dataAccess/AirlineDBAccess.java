package dataAccess;

import exception.*;
import model.*;
import model.Class;

import java.sql.*;
import java.util.*;

public class AirlineDBAccess {
    //region Search
    public static ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws SQLException, DataAccessException {
        try{
            ArrayList<SearchFlightsByDate> flights = new ArrayList<>();
            java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
            java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());
            SearchFlightsByDate flight;
            GregorianCalendar flightDepartureTime = new GregorianCalendar();
            GregorianCalendar flightArrivalTime = new GregorianCalendar();

            String sql =
                    "SELECT " +
                        "fli.number AS flightNumber, fli.departure_time AS flightDepartureTime, fli.arrival_time AS flightArrivalTime, " +
                        "depGate.terminal AS departureGateTerminal, depGate.number AS departureGateNumber, " +
                        "depAir.code AS departureAirportCode, depAir.name AS departureAirportName, depAir.country AS departureAirportCountry, " +
                        "arrGate.terminal AS arrivalGateTerminal, arrGate.number AS arrivalGateNumber, " +
                        "arrAir.code AS arrivalAirportCode, arrAir.name AS arrivalAirportName, arrAir.country AS arrivalAirportCountry, " +
                        "pla.plane_id AS planeId, pla.model AS planeModel, pla.brand AS planeBrand, " +
                        "pil.licence_number AS pilotLicenceNumber, pil.first_name AS pilotFirstName, pil.last_name AS pilotLastName " +
                    "FROM " +
                        "flight fli, " +
                        "gate depGate, " +
                        "airport depAir, " +
                        "gate arrGate, " +
                        "airport arrAir, " +
                        "plane pla, " +
                        "pilot pil " +
                    "WHERE " +
                        "fli.departure_gate = depGate.gate_id AND " +
                        "depGate.airport = depAir.code AND " +
                        "fli.arrival_gate = arrGate.gate_id AND " +
                        "arrGate.airport = arrAir.code AND " +
                        "fli.plane = pla.plane_id AND " +
                        "fli.pilot = pil.licence_number AND " +
                        "fli.departure_time BETWEEN ? AND ? " +
                    "ORDER BY " +
                        "fli.departure_time;"
                    ;
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
            preparedStatement.setDate(1, startDateSQL);
            preparedStatement.setDate(2, endDateSQL);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flightDepartureTime.setTime(data.getDate("flightDepartureTime"));
                flightArrivalTime.setTime(data.getDate("flightArrivalTime"));

                flight = new SearchFlightsByDate(
                        data.getString("flightNumber"),
                        flightDepartureTime,
                        flightArrivalTime,
                        data.getString("departureGateTerminal"),
                        data.getInt("departureGateNumber"),
                        data.getString("departureAirportCode"),
                        data.getString("departureAirportName"),
                        data.getString("departureAirportCountry"),
                        data.getString("arrivalGateTerminal"),
                        data.getInt("arrivalGateNumber"),
                        data.getString("arrivalAirportCode"),
                        data.getString("arrivalAirportName"),
                        data.getString("arrivalAirportCountry"),
                        data.getInt("planeId"),
                        data.getString("planeModel"),
                        data.getString("planeBrand"),
                        data.getString("pilotLicenceNumber"),
                        data.getString("pilotFirstName"),
                        data.getString("pilotLastName")
                );

                flights.add(flight);
        }
            return flights;

        }catch (DBConnectionException exception){
            throw new DataAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }
        return null;
    }
    public static ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(Class seatClass)
            throws SQLException {
        ArrayList<SearchPassengersByClass> passengers = new ArrayList<>();
        return null;
    }
    public static ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException {
        ArrayList<SearchFlightsByPilot> flights = new ArrayList<>();
        return null;
    }
    //endregion

    //region Get
    public static ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DBConnectionException {
        ArrayList<Flight> flights;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight ORDER BY departure_time");

        flights = flightResultSetIntoArrayList(data);

        return flights;
    }
    public static ArrayList<Pilot> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DBConnectionException {
        ArrayList<Pilot> pilots;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot ORDER BY first_name, last_name");

        pilots = pilotResultSetIntoArrayList(data);

        return pilots;
    }
    public static ArrayList<Airport> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DBConnectionException {
        ArrayList<Airport> airports;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM airport ORDER BY name");

        airports = airportResultSetIntoArrayList(data);

        return airports;
    }
    public static ArrayList<Plane> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DBConnectionException {
        ArrayList<Plane> planes;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM plane ORDER BY model, brand");

        planes = planeResultSetIntoArrayList(data);

        return planes;
    }
    public static ArrayList<Class> getAllClasses() throws SQLException, NameClassException, DBConnectionException {
        ArrayList<Class> classes;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM class ORDER BY name");

        classes = classResultSetIntoArrayList(data);

        return classes;
    }
    //endregion

    //region Edit
    public static void addFlight(Flight flightToAdd) throws SQLException, DBConnectionException {
        String sql =
                "INSERT INTO flight " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToAdd);

        preparedStatement.executeUpdate();
    }
    public static void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DBConnectionException {
        String sql =
                "UPDATE flight " +
                "SET number = ?, departure_time = ?, arrival_time = ?, is_meal_on_board = ?, " +
                "meal_description = ?, departure_gate = ?, arrival_gate = ?, pilot = ?, plane = ? " +
                "WHERE number = ?";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToUpdate);
        preparedStatement.setString(10, originalNumber);

        preparedStatement.executeUpdate();
    }
    public static void deleteFlight(Flight flightToDelete) throws SQLException, DBConnectionException {
        String sql =
                "DELETE FROM flight " +
                "WHERE number = ?";
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, flightToDelete.getNumber());

        preparedStatement.executeUpdate();
    }
    //endregion

    //region Tools
    private static ArrayList<Flight> flightResultSetIntoArrayList(ResultSet data) throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
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
    private static ArrayList<Pilot> pilotResultSetIntoArrayList(ResultSet data)
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException, PersonException.LastNameException,
            PersonException.EmailException, PilotException.LicenceNumberException, PilotException.FlyingFlightException {
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
    private static ArrayList<Airport> airportResultSetIntoArrayList(ResultSet data)
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException {
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
    private static ArrayList<Plane> planeResultSetIntoArrayList(ResultSet data)
            throws SQLException, PlaneException.ModelException, PlaneException.BrandException {
        ArrayList<Plane> planes = new ArrayList<>();
        Plane plane;

        while (data.next()) {
            plane = new Plane(
                    data.getInt("num"),
                    data.getString("model"),
                    data.getString("brand")
            );

            planes.add(plane);
        }

        return planes;
    }
    private static ArrayList<Class> classResultSetIntoArrayList(ResultSet data) throws SQLException, NameClassException {
        ArrayList<Class> classes = new ArrayList<>();
        Class classe;

        while (data.next()) {
            classe = new Class(
                    data.getInt("num"),
                    data.getString("name")
            );

            classes.add(classe);
        }

        return classes;
    }
    private static PreparedStatement preparedFlightStatement(String sql, Flight flight) throws SQLException, DBConnectionException {
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);

        preparedStatement.setString(1,  flight.getNumber());
        preparedStatement.setDate(2, new java.sql.Date(flight.getDepartureTime().getTimeInMillis()));
        preparedStatement.setDate(3, new java.sql.Date(flight.getArrivalTime().getTimeInMillis()));
        preparedStatement.setBoolean(4, flight.getMealOnBoard());
        if (flight.getMealDescription() == null) {
            preparedStatement.setNull(5, Types.VARCHAR);
        } else {
            preparedStatement.setString(5, flight.getMealDescription());
        }
        preparedStatement.setString(6, flight.getDepartureGate());
        preparedStatement.setString(7, flight.getArrivalGate());
        preparedStatement.setString(8, flight.getPilot());
        preparedStatement.setInt(9, flight.getNumberPlane());

        return preparedStatement;
    }
    //endregion

    //region Close
    public void closeConnection() throws DBCloseException {
        SingletonConnection.closeConnection();
    }
    //endregion
}
