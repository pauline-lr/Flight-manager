package dataAccess;

import exception.*;
import model.*;
import model.Class;
import pattern.DAO;

import java.sql.*;
import java.util.*;

public class AirlineDataBaseAccess implements DAO {
    //region Search
    public ArrayList<SearchFlightsByDate> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        ArrayList<SearchFlightsByDate> flights = new ArrayList<>();
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());
        SearchFlightsByDate flight;
        GregorianCalendar flightDepartureTime = new GregorianCalendar();
        GregorianCalendar flightArrivalTime = new GregorianCalendar();

        String sql =
            "SELECT " +
                "fli.number AS flightNumber, " +
                "fli.departure_time AS flightDepartureTime, " +
                "fli.arrival_time AS flightArrivalTime, " +
                "depGate.terminal AS departureGateTerminal, " +
                "depGate.number AS departureGateNumber, " +
                "depAir.code AS departureAirportCode, " +
                "depAir.name AS departureAirportName, " +
                "depAir.country AS departureAirportCountry, " +
                "arrGate.terminal AS arrivalGateTerminal, " +
                "arrGate.number AS arrivalGateNumber, " +
                "arrAir.code AS arrivalAirportCode, " +
                "arrAir.name AS arrivalAirportName, " +
                "arrAir.country AS arrivalAirportCountry, " +
                "pla.plane_id AS planeId, " +
                "pla.model AS planeModel, " +
                "pla.brand AS planeBrand, " +
                "pil.licence_number AS pilotLicenceNumber, " +
                "pil.first_name AS pilotFirstName, " +
                "pil.last_name AS pilotLastName " +
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

        try {
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

        } catch (DataBaseConnectionException exception){
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return flights;
    }
    public ArrayList<SearchPassengersByClass> getAllPassengersOfAClass(Class seatClass)
            throws DataBaseAccessException {
        ArrayList<SearchPassengersByClass> passengers = new ArrayList<>();
        SearchPassengersByClass passenger;
        GregorianCalendar flightDepartureTime = new GregorianCalendar();
        GregorianCalendar flightArrivalTime = new GregorianCalendar();

        String sql =
            "SELECT " +
                "pas.passport_number AS passengerPassportNumber, " +
                "pas.first_name AS passengerFirstName, " +
                "pas.last_name AS passengerLastName, " +
                "sea.seat_row AS seatRow," +
                "sea.seat_column AS seatColumn, " +
                "fli.number AS flightNumber, " +
                "fli.departure_time AS flightDepartureTime, " +
                "fli.arrival_time AS flightArrivalTime, " +
                "depAir.code AS departureAirportCode, " +
                "depAir.name AS departureAirportName, " +
                "depAir.country AS departureAirportCountry, " +
                "arrAir.code AS arrivalAirportCode, " +
                "arrAir.name AS arrivalAirportName, " +
                "arrAir.country AS arrivalAirportCountry " +
            "FROM " +
                "passenger pas, " +
                "seat sea, " +
                "class cla, " +
                "flight fli, " +
                "gate depGate, " +
                "airport depAir, " +
                "gate arrGate, " +
                "airport arrAir " +
            "WHERE " +
                "sea.flight = fli.number AND " +
                "sea.passenger = pas.passport_number AND " +
                "sea.seat_class = cla.class_id AND " +
                "fli.departure_gate = depGate.gate_id AND " +
                "depGate.airport = depAir.code AND " +
                "fli.arrival_gate = arrGate.gate_id AND " +
                "arrGate.airport = arrAir.code AND " +
                "cla.name = ? " +
            "ORDER BY " +
                "pas.last_name, pas.first_name;"
        ;

        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, seatClass.getName());

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flightDepartureTime.setTime(data.getDate("flightDepartureTime"));
                flightArrivalTime.setTime(data.getDate("flightArrivalTime"));

                passenger = new SearchPassengersByClass(
                    data.getString("passengerPassportNumber"),
                    data.getString("passengerFirstName"),
                    data.getString("passengerLastName"),
                    data.getInt("seatRow"),
                    data.getString("seatColumn"),
                    data.getString("flightNumber"),
                    flightDepartureTime,
                    flightArrivalTime,
                    data.getString("departureAirportCode"),
                    data.getString("departureAirportName"),
                    data.getString("departureAirportCountry"),
                    data.getString("arrivalAirportCode"),
                    data.getString("arrivalAirportName"),
                    data.getString("arrivalAirportCountry")
                );

                passengers.add(passenger);
            }

        } catch (DataBaseConnectionException exception){
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return passengers;
    }
    public ArrayList<SearchFlightsByPilot> getAllFlightsOfAPilot(Pilot pilot)
            throws SQLException, DataBaseAccessException {
        ArrayList<SearchFlightsByPilot> flights = new ArrayList<>();
        SearchFlightsByPilot flight;
        GregorianCalendar flightDepartureTime = new GregorianCalendar();
        GregorianCalendar flightArrivalTime = new GregorianCalendar();

        String sql =
            "SELECT " +
                "fli.number AS flightNumber, " +
                "fli.departure_time AS flightDepartureTime, " +
                "fli.arrival_time AS flightArrivalTime, " +
                "pla.plane_id AS planeId, " +
                "pla.model AS planeModel, " +
                "pla.brand AS planeBrand, " +
                "depAir.code AS departureAirportCode, " +
                "depAir.name AS departureAirportName, " +
                "depAir.country AS departureAirportCountry, " +
                "arrAir.code AS arrivalAirportCode, " +
                "arrAir.name AS arrivalAirportName, " +
                "arrAir.country AS arrivalAirportCountry " +
            "FROM " +
                "flight fli, " +
                "plane pla, " +
                "pilot pil, " +
                "gate depGate, " +
                "airport depAir, " +
                "gate arrGate, " +
                "airport arrAir " +
            "WHERE " +
                "fli.plane = pla.plane_id AND " +
                "fli.pilot = pil.licence_number AND " +
                "fli.departure_gate = depGate.gate_id AND " +
                "depGate.airport = depAir.code AND " +
                "fli.arrival_gate = arrGate.gate_id AND " +
                "arrGate.airport = arrAir.code AND " +
                "pil.last_name = ? " +
            "ORDER BY " +
                "departure_time;";
        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, pilot.getLicenceNumber());

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flightDepartureTime.setTime(data.getDate("flightDepartureTime"));
                flightArrivalTime.setTime(data.getDate("flightArrivalTime"));

                flight = new SearchFlightsByPilot(
                        data.getString("flightNumber"),
                        flightDepartureTime,
                        flightArrivalTime,
                        data.getInt("planeId"),
                        data.getString("planeModel"),
                        data.getString("planeBrand"),
                        data.getString("departureAirportCode"),
                        data.getString("departureAirportName"),
                        data.getString("departureAirportCountry"),
                        data.getString("arrivalAirportCode"),
                        data.getString("arrivalAirportName"),
                        data.getString("arrivalAirportCountry")
                );

                flights.add(flight);
            }

        } catch (DataBaseConnectionException exception){
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return flights;
    }
    //endregion

    //region Get
    public ArrayList<Flight> getAllFlights()
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException, DataBaseConnectionException {
        ArrayList<Flight> flights;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM flight ORDER BY departure_time");

        flights = flightResultSetIntoArrayList(data);

        return flights;
    }
    public ArrayList<String> getAllPilots()
            throws SQLException, PersonException.PhoneNumberException, PersonException.FirstNameException,
            PersonException.LastNameException, PersonException.EmailException, PilotException.LicenceNumberException,
            PilotException.FlyingFlightException, DataBaseConnectionException {
        ArrayList<String> pilots = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot ORDER BY licence_number");

        while (data.next()) {
            pilots.add(data.getString("licence_number") + " - " + data.getString("last_name") + " " +data.getString("first_name"));
        }

        return pilots;
    }
    public ArrayList<String> getAllAirports()
            throws SQLException, AiportException.CodeException, AiportException.NameAirportException, AiportException.CountryException, DataBaseConnectionException {
        ArrayList<String> airports = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM airport ORDER BY code");

        while (data.next()) {
            airports.add(data.getString("code") + " - " + data.getString("name") + " - " + data.getString("country"));
        }

        return airports;
    }
    public ArrayList<String> getAllPlanes() throws SQLException, PlaneException.ModelException, PlaneException.BrandException, DataBaseConnectionException {
        ArrayList<String> planes = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM plane ORDER BY plane_id");

        while (data.next()) {
            planes.add(data.getInt("plane_id") + " - " + data.getString("brand") + " " + data.getString("model"));
        }

        return planes;
    }
    public ArrayList<Class> getAllClasses() throws SQLException, NameClassException, DataBaseConnectionException {
        ArrayList<Class> classes;

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM class ORDER BY name");

        classes = classResultSetIntoArrayList(data);

        return classes;
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd) throws SQLException, DataBaseConnectionException {
        String sql =
                "INSERT INTO flight " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToAdd);

        preparedStatement.executeUpdate();
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber) throws SQLException, DataBaseConnectionException {
        String sql =
                "UPDATE flight " +
                "SET number = ?, departure_time = ?, arrival_time = ?, is_meal_on_board = ?, " +
                "meal_description = ?, departure_gate = ?, arrival_gate = ?, pilot = ?, plane = ? " +
                "WHERE number = ?";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToUpdate);
        preparedStatement.setString(10, originalNumber);

        preparedStatement.executeUpdate();
    }
    public void deleteFlight(Flight flightToDelete) throws SQLException, DataBaseConnectionException {
        String sql =
                "DELETE FROM flight " +
                "WHERE number = ?";
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, flightToDelete.getNumber());

        preparedStatement.executeUpdate();
    }
    //endregion

    //region Connection
    public void closeConnection() throws DataBaseCloseException {
        SingletonConnection.closeConnection();
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
    private static PreparedStatement preparedFlightStatement(String sql, Flight flight) throws SQLException, DataBaseConnectionException {
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
}