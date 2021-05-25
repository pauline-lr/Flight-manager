package dataAccess;

import exception.*;
import exception.dataBase.*;
import model.*;
import model.search.*;
import pattern.DataAccessObjectPattern;
import tool.Format;

import java.sql.*;
import java.util.*;

public class AirlineDataBaseAccess implements DataAccessObjectPattern {
    //region Get
    public Flight getFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        String sqlRequest = "SELECT * FROM flight WHERE number = ?";
        Flight flight = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            flight = getFlightFromResultSet(data);
        }

        return flight;
    }

    public ArrayList<Flight> getAllFlights()
            throws SQLException, DataBaseConnectionException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        String sqlRequest = "SELECT * FROM flight WHERE departure_time > ? ORDER BY number";
        java.sql.Timestamp currentDateSQL = new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis());
        ArrayList<Flight> flights = new ArrayList<>();

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setTimestamp(1, currentDateSQL);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {
            flights.add(getFlightFromResultSet(data));
        }

        return flights;
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT " +
                "fli.number AS flightNumber, " +
                "fli.departure_time AS departureTime, " +
                "fli.arrival_time AS arrivalTime, " +
                "depGat.terminal AS departureTerminal, " +
                "depGat.number AS departureGate, " +
                "depAir.code AS departureAirport, " +
                "arrGat.terminal AS arrivalTerminal, " +
                "arrGat.number AS arrivalGate, " +
                "arrAir.code AS arrivalAirport " +
                "FROM " +
                "flight fli, " +
                "gate depGat, " +
                "airport depAir, " +
                "gate arrGat, " +
                "airport arrAir " +
                "WHERE " +
                "fli.departure_gate = depGat.gate_id AND " +
                "depGat.airport = depAir.code AND " +
                "fli.arrival_gate = arrGat.gate_id AND " +
                "arrGat.airport = arrAir.code AND " +
                "fli.number = ? " +
                "ORDER BY " +
                "departure_time;";
        String flight = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            flight = getFlightToStringResultSet(data);
        }

        return flight;
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT licence_number, first_name, last_name FROM pilot WHERE licence_number = ?";
        String pilotToString = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, pilotLicenceNumber);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            pilotToString = data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name");
        }

        return pilotToString;
    }

    public String getPlaneToString(Integer planeID)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT plane_id, model, brand FROM plane WHERE plane_id = ?";
        String planeToString = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setInt(1, planeID);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            planeToString = data.getInt("plane_id") + " - " + data.getString("brand") + " " + data.getString("model");
        }

        return planeToString;
    }

    public String getAirportToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT code, name, country FROM gate, airport WHERE airport = code AND gate_id = ?";
        String airportToString = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, gateID);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            airportToString = data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country");
        }

        return airportToString;
    }

    public String getTerminalToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT terminal FROM gate WHERE gate_id = ?";
        String gateToString = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, gateID);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            gateToString = data.getString("terminal");
        }

        return gateToString;
    }

    public String getGateToString(String gateID)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT number FROM gate WHERE gate_id = ?";
        String gateToString = null;

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, gateID);

        ResultSet data = preparedStatement.executeQuery();

        if (data.next()) {
            gateToString = Integer.toString(data.getInt("number"));
        }

        return gateToString;
    }

    public String[] getAllFlightsToString()
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT " +
                "fli.number AS flightNumber, " +
                "fli.departure_time AS departureTime, " +
                "fli.arrival_time AS arrivalTime, " +
                "depGat.terminal AS departureTerminal, " +
                "depGat.number AS departureGate, " +
                "depAir.code AS departureAirport, " +
                "arrGat.terminal AS arrivalTerminal, " +
                "arrGat.number AS arrivalGate, " +
                "arrAir.code AS arrivalAirport " +
                "FROM " +
                "flight fli, " +
                "gate depGat, " +
                "airport depAir, " +
                "gate arrGat, " +
                "airport arrAir " +
                "WHERE " +
                "fli.departure_gate = depGat.gate_id AND " +
                "depGat.airport = depAir.code AND " +
                "fli.arrival_gate = arrGat.gate_id AND " +
                "arrGat.airport = arrAir.code AND " +
                "fli.departure_time > ?" +
                "ORDER BY " +
                "departure_time;";
        java.sql.Timestamp currentDateSQL = new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis());
        ArrayList<String> flights = new ArrayList<>();

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setTimestamp(1, currentDateSQL);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {

            flights.add(getFlightToStringResultSet(data));
        }

        return flights.toArray(new String[0]);
    }

    public String[] getAllPilotsToString()
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT * FROM pilot ORDER BY licence_number;";
        ArrayList<String> pilotLicenceNumbers = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery(sqlRequest);

        while (data.next()) {
            pilotLicenceNumbers.add(data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name"));
        }

        return pilotLicenceNumbers.toArray(new String[0]);
    }

    public String[] getAllPlanesToString()
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT * FROM plane ORDER BY plane_id;";
        ArrayList<String> planeIDs = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery(sqlRequest);

        while (data.next()) {
            planeIDs.add(data.getString("plane_id") + " - " + data.getString("brand") + " " + data.getString("model"));
        }

        return planeIDs.toArray(new String[0]);
    }

    public String[] getAllClassesToString()
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT name FROM class ORDER BY class_id DESC;";
        ArrayList<String> classNames = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery(sqlRequest);

        while (data.next()) {
            classNames.add(data.getString("name"));
        }
        return classNames.toArray(new String[0]);
    }

    public String[] getAllAirportsToString()
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT * FROM airport ORDER BY code;";
        ArrayList<String> airportCodes = new ArrayList<>();

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery(sqlRequest);

        while (data.next()) {
            airportCodes.add(data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country"));
        }

        return airportCodes.toArray(new String[0]);
    }

    public String[] getAllTerminalsOfAnAirportToString(String airportCode)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT DISTINCT terminal FROM gate, airport WHERE airport = code AND code = ? ORDER BY terminal;";
        ArrayList<String> terminalsOfAnAirport = new ArrayList<>();

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, airportCode);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {
            terminalsOfAnAirport.add(data.getString("terminal"));
        }

        return terminalsOfAnAirport.toArray(new String[0]);
    }

    public String[] getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT number FROM gate, airport WHERE airport = code AND code = ? AND terminal = ? ORDER BY number;";
        ArrayList<String> gatesOfAnAirportAndTerminal = new ArrayList<>();

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, airportCode);
        preparedStatement.setString(2, terminal);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {
            gatesOfAnAirportAndTerminal.add(Integer.toString(data.getInt("number")));
        }

        return gatesOfAnAirportAndTerminal.toArray(new String[0]);
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        String sqlRequest = "SELECT " +
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
                "fli.departure_time;";
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());
        ArrayList<FlightsBetweenDatesSearch> flights = new ArrayList<>();
        FlightsBetweenDatesSearch flight;

        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setDate(1, startDateSQL);
            preparedStatement.setDate(2, endDateSQL);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar flightDepartureTime = new GregorianCalendar();
                GregorianCalendar flightArrivalTime = new GregorianCalendar();

                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

                flight = new FlightsBetweenDatesSearch(
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

        } catch (DataBaseConnectionException exception) {
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return flights;
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws DataBaseAccessException {
        String sqlRequest = "SELECT " +
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
                "pas.last_name, pas.first_name;";
        ArrayList<PassengersByClassSearch> passengers = new ArrayList<>();
        PassengersByClassSearch passenger;

        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, className);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar flightDepartureTime = new GregorianCalendar();
                GregorianCalendar flightArrivalTime = new GregorianCalendar();

                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

                passenger = new PassengersByClassSearch(
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

        } catch (DataBaseConnectionException exception) {
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return passengers;
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws DataBaseAccessException {
        String sqlRequest = "SELECT " +
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
                "gate depGat, " +
                "airport depAir, " +
                "gate arrGat, " +
                "airport arrAir " +
                "WHERE " +
                "fli.plane = pla.plane_id AND " +
                "fli.pilot = pil.licence_number AND " +
                "fli.departure_gate = depGat.gate_id AND " +
                "depGat.airport = depAir.code AND " +
                "fli.arrival_gate = arrGat.gate_id AND " +
                "arrGat.airport = arrAir.code AND " +
                "pil.licence_number = ? " +
                "ORDER BY " +
                "departure_time;";
        ArrayList<FlightsByPilotSearch> flights = new ArrayList<>();
        FlightsByPilotSearch flight;

        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, pilotLicenceNumber);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                GregorianCalendar flightDepartureTime = new GregorianCalendar();
                GregorianCalendar flightArrivalTime = new GregorianCalendar();

                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

                flight = new FlightsByPilotSearch(
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

        } catch (DataBaseConnectionException exception) {
            throw new DataBaseAccessException();
        } catch (SQLException exception) {
            exception.getMessage();
        }

        return flights;
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "INSERT INTO flight VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);

        preparedFlightStatement(flight, preparedStatement).executeUpdate();
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException {
        String CheckRequest = "SET foreign_key_checks = 1;";
        String sqlRequest = "UPDATE flight " +
                "SET " +
                "number = ?, " +
                "departure_time = ?, " +
                "arrival_time = ?, " +
                "is_meal_on_board = ?, " +
                "meal_description = ?, " +
                "departure_gate = ?, " +
                "arrival_gate = ?, " +
                "pilot = ?, " +
                "plane = ? " +
                "WHERE number = ?";

        updateSeatsOfAFlight(flight.getNumber(), originalFlightNumber);

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(10, originalFlightNumber);

        preparedFlightStatement(flight, preparedStatement).executeUpdate();

        Statement statement = SingletonConnection.getInstance().createStatement();
        statement.executeQuery(CheckRequest);
    }

    public void deleteFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "DELETE FROM flight WHERE number = ?";

        deleteSeatsOfAFlight(flightNumber);

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);

        preparedStatement.executeUpdate();
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "SELECT number FROM flight WHERE number = ?";

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);

        ResultSet data = preparedStatement.executeQuery();

        return data.next();
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        SingletonConnection.closeConnection();
    }
    //endregion

    //region Tools
    private Flight getFlightFromResultSet(ResultSet data)
            throws SQLException, FlightException.MealDescriptionException, FlightException.NumberFlightException {
        Flight flight;
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        String mealDescription;

        departureTime.setTime(data.getTimestamp("departure_time"));
        arrivalTime.setTime(data.getTimestamp("arrival_time"));

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

        return flight;
    }

    private String getFlightToStringResultSet(ResultSet data)
            throws SQLException {
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();

        departureTime.setTime(data.getTimestamp("departureTime"));
        arrivalTime.setTime(data.getTimestamp("arrivalTime"));

        String departureInformation =
                "DÉPART : " + Format.timeFormat(departureTime) + " " + Format.dateFormat(departureTime) + ", " +
                        data.getString("departureTerminal") + data.getInt("departureGate") + ", " + data.getString("departureAirport");
        String arrivalInformation =
                "ARRIVÉE : " + Format.timeFormat(arrivalTime) + " " + Format.dateFormat(arrivalTime) + ", " +
                        data.getString("arrivalTerminal") + data.getInt("arrivalGate") + ", " + data.getString("arrivalAirport");

        return data.getString("flightNumber") + " - " + departureInformation + " - " + arrivalInformation;
    }

    private PreparedStatement preparedFlightStatement(Flight flight, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setString(1, flight.getNumber());
        preparedStatement.setTimestamp(2, new java.sql.Timestamp(flight.getDepartureTime().getTimeInMillis()));
        preparedStatement.setTimestamp(3, new java.sql.Timestamp(flight.getArrivalTime().getTimeInMillis()));
        preparedStatement.setBoolean(4, flight.getMealOnBoard());
        if (flight.getMealDescription() == null || !flight.getMealOnBoard()) {
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

    private void updateSeatsOfAFlight(String flightNumber, String originalFlightNumber)
            throws SQLException, DataBaseConnectionException {
        String CheckRequest = "SET foreign_key_checks = 0;";
        String sqlRequest = "UPDATE seat SET flight = ? WHERE flight = ?;";

        Statement statement = SingletonConnection.getInstance().createStatement();
        statement.executeQuery(CheckRequest);

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);
        preparedStatement.setString(2, originalFlightNumber);

        preparedStatement.executeUpdate();
    }

    private void deleteSeatsOfAFlight(String flightNumber)
            throws SQLException, DataBaseConnectionException {
        String sqlRequest = "DELETE FROM seat WHERE flight = ?";

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
        preparedStatement.setString(1, flightNumber);

        preparedStatement.executeUpdate();
    }
    //endregion
}
