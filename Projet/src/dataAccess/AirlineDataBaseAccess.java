package dataAccess;

import exception.*;
import model.*;
import model.search.FlightsBetweenDatesSearch;
import model.search.FlightsByPilotSearch;
import model.search.PassengersByClassSearch;
import pattern.DataAccessObjectPattern;
import tool.Format;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class AirlineDataBaseAccess implements DataAccessObjectPattern {
    //region Get
    public Flight getFlight(String flightNumber)
            throws ConnectionException, RetrievalException, FlightException.NumberFlightException {
        Flight flight = null;

        try {
            String sqlRequest = "SELECT * FROM flight WHERE number = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                flight = getFlightFromResultSet(data);
            }
        }catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flight;
    }

    public ArrayList<Flight> getAllFlights()
            throws ConnectionException, RetrievalException {
        ArrayList<Flight> flights = new ArrayList<>();

        try {
            String sqlRequest = "SELECT * FROM flight WHERE departure_time > ? ORDER BY number";
            java.sql.Timestamp currentDateSQL = new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis());

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setTimestamp(1, currentDateSQL);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flights.add(getFlightFromResultSet(data));
            }
        } catch (IOException exception) {
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flights;
    }
    //endregion

    //region Get to String
    public String getFlightToString(String flightNumber)
            throws ConnectionException, RetrievalException {
        String flight = null;

        try {
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


            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                flight = getFlightToStringResultSet(data);
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flight;
    }

    public String getPilotToString(String pilotLicenceNumber)
            throws ConnectionException, RetrievalException {
        String pilotToString = null;

        try {
            String sqlRequest = "SELECT licence_number, first_name, last_name FROM pilot WHERE licence_number = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, pilotLicenceNumber);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                pilotToString = data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name");
            }
        }catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        }catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return pilotToString;
    }

    public String getPlaneToString(Integer planeID)
            throws ConnectionException, RetrievalException {
        String planeToString = null;

        try {
            String sqlRequest = "SELECT plane_id, model, brand FROM plane WHERE plane_id = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setInt(1, planeID);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                planeToString = data.getInt("plane_id") + " - " + data.getString("brand") + " " + data.getString("model");
            }
        } catch (IOException exception) {
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return planeToString;
    }

    public String getAirportToString(String gateID)
            throws ConnectionException, RetrievalException {
        String airportToString = null;

        try {
            String sqlRequest = "SELECT code, name, country FROM gate, airport WHERE airport = code AND gate_id = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, gateID);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                airportToString = data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country");
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return airportToString;
    }

    public String getTerminalToString(String gateID)
            throws ConnectionException, RetrievalException {
        String gateToString = null;

        try {
            String sqlRequest = "SELECT terminal FROM gate WHERE gate_id = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, gateID);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                gateToString = data.getString("terminal");
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        }catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return gateToString;
    }

    public String getGateToString(String gateID)
            throws ConnectionException, RetrievalException {
        String gateToString = null;

        try {
            String sqlRequest = "SELECT number FROM gate WHERE gate_id = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, gateID);

            ResultSet data = preparedStatement.executeQuery();

            if (data.next()) {
                gateToString = Integer.toString(data.getInt("number"));
            }
        }catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        }catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return gateToString;
    }

    public ArrayList<String> getAllFlightsToString()
            throws RetrievalException, ConnectionException {
        ArrayList<String> flights = new ArrayList<>();

        try {
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

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setTimestamp(1, currentDateSQL);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flights.add(getFlightToStringResultSet(data));
            }

        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flights;
    }

    public ArrayList<String> getAllPilotsToString()
            throws ConnectionException, RetrievalException {
        ArrayList<String> pilotLicenceNumbers = new ArrayList<>();

        try {
            String sqlRequest = "SELECT * FROM pilot ORDER BY licence_number;";

            Statement statement = SingletonConnection.getInstance().createStatement();
            ResultSet data = statement.executeQuery(sqlRequest);

            while (data.next()) {
                pilotLicenceNumbers.add(data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name"));
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return pilotLicenceNumbers;
    }


    public ArrayList<String> getLastPilotFlightArrivingAt(GregorianCalendar date, String airportID)
            throws RetrievalException, ConnectionException {
        ArrayList<String> pilotList = new ArrayList<>();

        try {
            String sqlRequest =
                    "SELECT " +
                    "pilot.licence_number AS pilotLicenceNumber, " +
                    "pilot.last_name AS pilotLastName, " +
                    "pilot.first_name AS pilotFirstName " +
                    "FROM pilot, flight, gate " +
                    "WHERE " +
                    "flight.pilot = pilot.licence_number AND " +
                    "flight.arrival_gate = gate.gate_id AND " +
                    "flight.arrival_time = ANY (" +
                        "SELECT max(flight.arrival_time) AS maxArrivalTime " +
                        "FROM flight " +
                        "WHERE " +
                        "flight.arrival_time < ? AND " +
                        "flight.pilot IN (SELECT licence_number FROM pilot)" +
                        "GROUP BY flight.pilot " +
                    ") AND " +
                    "flight.pilot = ANY (" +
                        "SELECT flight.pilot AS pilotFlight " +
                        "FROM flight " +
                        "WHERE " +
                        "flight.arrival_time < ? AND " +
                        "flight.pilot IN (SELECT licence_number FROM pilot) " +
                        "GROUP BY flight.pilot " +
                        "HAVING max(flight.arrival_time) " +
                    ") AND " +
                    "gate.airport = ?";

            java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTimeInMillis());
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setTimestamp(1, dateSQL);
            preparedStatement.setTimestamp(2, dateSQL);
            preparedStatement.setString(3, airportID);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                pilotList.add(data.getString("pilotLicenceNumber") + " - " + data.getString("pilotLastName") + " " + data.getString("pilotFirstName"));
            }

        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return pilotList;
    }


    public ArrayList<String> getAllAvailablePilotsToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException {
        ArrayList<String> pilotLicenceNumbers = new ArrayList<>();

        try {
            String sqlRequest =
                "SELECT * FROM pilot " +
                "WHERE licence_number NOT IN " +
                    "(SELECT pilot FROM flight " +
                    "WHERE ? BETWEEN departure_time AND arrival_time) " +
                "ORDER BY licence_number;";

            java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTimeInMillis());
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setTimestamp(1, dateSQL);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                pilotLicenceNumbers.add(data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name"));
            }

        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return pilotLicenceNumbers;
    }

    public ArrayList<String> getAllAvailablePlanesToString(GregorianCalendar date)
            throws ConnectionException, RetrievalException {
        ArrayList<String> planeIDs = new ArrayList<>();

        try {
            String sqlRequest =
                "SELECT * FROM plane " +
                "WHERE plane_id NOT IN " +
                    "(SELECT plane FROM flight " +
                    "WHERE ? BETWEEN departure_time AND arrival_time) " +
                "ORDER BY plane_id;";

            java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTimeInMillis());
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setTimestamp(1, dateSQL);
            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                planeIDs.add(data.getString("plane_id") + " - " + data.getString("brand") + " " + data.getString("model"));
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return planeIDs;
    }

    public ArrayList<String> getAllClassesToString()
            throws ConnectionException, RetrievalException {
        ArrayList<String> classNames = new ArrayList<>();

        try {
            String sqlRequest = "SELECT name FROM class ORDER BY class_id DESC;";

            Statement statement = SingletonConnection.getInstance().createStatement();
            ResultSet data = statement.executeQuery(sqlRequest);

            while (data.next()) {
                classNames.add(data.getString("name"));
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }
        return classNames;
    }

    public ArrayList<String> getAllAirportsToString()
            throws ConnectionException, RetrievalException {
        ArrayList<String> airportCodes = new ArrayList<>();

        try {
            String sqlRequest = "SELECT * FROM airport ORDER BY code;";

            Statement statement = SingletonConnection.getInstance().createStatement();
            ResultSet data = statement.executeQuery(sqlRequest);

            while (data.next()) {
                airportCodes.add(data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country"));
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return airportCodes;
    }

    public ArrayList<String> getAllTerminalsOfAnAirportToString(String airportCode)
            throws ConnectionException, RetrievalException {
        ArrayList<String> terminalsOfAnAirport = new ArrayList<>();

        try {
            String sqlRequest = "SELECT DISTINCT terminal FROM gate, airport WHERE airport = code AND code = ? ORDER BY terminal;";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, airportCode);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                terminalsOfAnAirport.add(data.getString("terminal"));
            }

        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return terminalsOfAnAirport;
    }

    public ArrayList<String> getAllGatesOfAnAirportAndTerminalToString(String airportCode, String terminal)
            throws ConnectionException, RetrievalException {
        ArrayList<String> gatesOfAnAirportAndTerminal = new ArrayList<>();

        try {
            String sqlRequest = "SELECT number FROM gate, airport WHERE airport = code AND code = ? AND terminal = ? ORDER BY number;";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, airportCode);
            preparedStatement.setString(2, terminal);

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                gatesOfAnAirportAndTerminal.add(Integer.toString(data.getInt("number")));
            }
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return gatesOfAnAirportAndTerminal;
    }
    //endregion

    //region Search
    public ArrayList<FlightsBetweenDatesSearch> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws RetrievalException, ConnectionException {
        ArrayList<FlightsBetweenDatesSearch> flights = new ArrayList<>();

        try {
            FlightsBetweenDatesSearch flight;
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

        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flights;
    }

    public ArrayList<PassengersByClassSearch> getAllPassengersOfAClass(String className)
            throws RetrievalException, ConnectionException {
        ArrayList<PassengersByClassSearch> passengers = new ArrayList<>();

        try{
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
            PassengersByClassSearch passenger;
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
        } catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return passengers;
    }

    public ArrayList<FlightsByPilotSearch> getAllFlightsOfAPilot(String pilotLicenceNumber)
            throws RetrievalException, ConnectionException {
        ArrayList<FlightsByPilotSearch> flights = new ArrayList<>();

        try {
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
            FlightsByPilotSearch flight;

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

        }catch (IOException exception){
            throw new RetrievalException(exception.getMessage());
        }catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage());
        }

        return flights;
    }
    //endregion

    //region Edit
    public void addFlight(Flight flight)
            throws AddException, ConnectionException {
        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlRequest = "INSERT INTO flight VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);

            preparedFlightStatement(flight, preparedStatement).executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException | RetrievalException exception) {
            throw new AddException(exception.getMessage());
        }
    }

    public void modifyFlight(Flight flight, String originalFlightNumber)
            throws ConnectionException, ModifyException {
        try {
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
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException | RetrievalException exception) {
            throw new ModifyException(exception.getMessage());
        }
    }

    public void deleteFlight(String flightNumber)
            throws ConnectionException, ModifyException {
        try {
            String sqlRequest = "DELETE FROM flight WHERE number = ?";

            deleteSeatsOfAFlight(flightNumber);

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);

            preparedStatement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException | RetrievalException exception) {
            throw new ModifyException(exception.getMessage());
        }
    }
    //endregion

    //region Test
    public Boolean flightNumberIsExisting(String flightNumber)
            throws ConnectionException, ModifyException {
        Boolean dataNext;
        try {
            String sqlRequest = "SELECT number FROM flight WHERE number = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);

            ResultSet data = preparedStatement.executeQuery();
            dataNext = data.next();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new ModifyException(exception.getMessage());
        }

        return dataNext;
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws CloseException, ConnectionException {
        try{
            SingletonConnection.closeConnection();
        } catch (SQLException exception) {
            throw new CloseException(exception.getMessage());
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        }
    }
    //endregion


    //region Tools
    private Flight getFlightFromResultSet(ResultSet data)
            throws RetrievalException {
        Flight flight;

        try {
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
        } catch (SQLException | NotMatchException | TextLengthException exception) {
            throw new RetrievalException(exception.getMessage());
        }

        return flight;
    }

    private String getFlightToStringResultSet(ResultSet data)
            throws RetrievalException {
        try {
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

        } catch (SQLException exception) {
            throw new RetrievalException(exception.getMessage());
        }
    }

    private PreparedStatement preparedFlightStatement(Flight flight, PreparedStatement preparedStatement)
            throws RetrievalException {
        try {
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
        } catch (SQLException exception) {
            throw new RetrievalException(exception.getMessage());
        }

        return preparedStatement;
    }

    private void updateSeatsOfAFlight(String flightNumber, String originalFlightNumber)
            throws ConnectionException, RetrievalException {
        try {
            String CheckRequest = "SET foreign_key_checks = 0;";
            String sqlRequest = "UPDATE seat SET flight = ? WHERE flight = ?;";

            Statement statement = SingletonConnection.getInstance().createStatement();
            statement.executeQuery(CheckRequest);

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);
            preparedStatement.setString(2, originalFlightNumber);

            preparedStatement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new RetrievalException(exception.getMessage());
        }
    }

    private void deleteSeatsOfAFlight(String flightNumber)
            throws ConnectionException, RetrievalException {
        try {
            String sqlRequest = "DELETE FROM seat WHERE flight = ?";

            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sqlRequest);
            preparedStatement.setString(1, flightNumber);

            preparedStatement.executeUpdate();
        } catch (IOException exception) {
            throw new ConnectionException(exception.getMessage());
        } catch (SQLException exception) {
            throw new RetrievalException(exception.getMessage());
        }
    }
    //endregion
}