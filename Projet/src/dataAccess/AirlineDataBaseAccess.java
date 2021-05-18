package dataAccess;

import exception.*;
import model.*;
import model.Class;
import pattern.*;

import java.sql.*;
import java.util.*;

public class AirlineDataBaseAccess implements DAO {
    //region Search
    public ArrayList<SearchFlightsBetweenDates> getAllFlightsBetweenDates(GregorianCalendar startDate, GregorianCalendar endDate)
            throws DataBaseAccessException {
        ArrayList<SearchFlightsBetweenDates> flights = new ArrayList<>();
        java.sql.Date startDateSQL = new java.sql.Date(startDate.getTimeInMillis());
        java.sql.Date endDateSQL = new java.sql.Date(endDate.getTimeInMillis());
        SearchFlightsBetweenDates flight;
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
                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

                flight = new SearchFlightsBetweenDates(
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
                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

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
            throws DataBaseAccessException {
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
                "pil.last_name = ? " +
            "ORDER BY " +
                "departure_time;";
        try {
            PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, pilot.getLicenceNumber());

            ResultSet data = preparedStatement.executeQuery();

            while (data.next()) {
                flightDepartureTime.setTime(data.getTimestamp("flightDepartureTime"));
                flightArrivalTime.setTime(data.getTimestamp("flightArrivalTime"));

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
    public ArrayList<Flight> getAllFlights() {
        ArrayList<Flight> flights =  new ArrayList<>();
        Flight flight;
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        String mealDescription;

        try {
            PreparedStatement statement = SingletonConnection.getInstance().prepareStatement("SELECT * FROM flight ORDER BY number");
            ResultSet data = statement.executeQuery();
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
        } catch (SQLException | DataBaseConnectionException | FlightException.NumberFlightException | FlightException.MealDescriptionException throwables) {
            throwables.printStackTrace();
        }

        return flights;
    }
    public String [] getAllFlightsForComboBox()
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> flights = new ArrayList<>();
        GregorianCalendar departureTime = new GregorianCalendar();
        GregorianCalendar arrivalTime = new GregorianCalendar();
        String sql =
            "SELECT " +
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
                "arrGat.airport = arrAir.code " +
            "ORDER BY " +
                "departure_time;";

        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery(sql);

        while(data.next()) {
            departureTime.setTime(data.getTimestamp("departureTime"));
            arrivalTime.setTime(data.getTimestamp("arrivalTime"));

            String departureInfos =
                "DÉPART : " + departureTime.get(Calendar.HOUR_OF_DAY) + ":" + departureTime.get(Calendar.MINUTE) + " " +
                departureTime.get(Calendar.DAY_OF_MONTH) + "/" + departureTime.get(Calendar.MONTH) + "/" + departureTime.get(Calendar.YEAR) + ", " +
                data.getString("departureTerminal") + data.getInt("departureGate") + ", " + data.getString("departureAirport")
            ;
            String arrivalMoment =
                "ARRIVÉE : " + arrivalTime.get(Calendar.HOUR_OF_DAY) + ":" + arrivalTime.get(Calendar.MINUTE) + " " +
                arrivalTime.get(Calendar.DAY_OF_MONTH) + "/" + arrivalTime.get(Calendar.MONTH) + "/" + arrivalTime.get(Calendar.YEAR) + ", " +
                data.getString("arrivalTerminal") + data.getInt("arrivalGate") + ", " + data.getString("arrivalAirport")
            ;

            flights.add(data.getString("flightNumber") + " - " + departureInfos + " - " + arrivalMoment);
        }

        return flights.toArray(new String[0]);
    }
    public String [] getAllPilotsForComboBox()
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> pilotLicenceNumbers = new ArrayList<>();
        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM pilot ORDER BY licence_number");

        while(data.next()) {
            pilotLicenceNumbers.add(data.getString("licence_number") + " - " + data.getString("last_name") + " " + data.getString("first_name"));
        }

        return pilotLicenceNumbers.toArray(new String[0]);
    }
    public String [] getAllPlanesForComboBox()
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> planeIDs = new ArrayList<>();
        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM plane ORDER BY plane_id");

        while(data.next()) {
            planeIDs.add(data.getString("plane_id") + " - " + data.getString("brand") + " " + data.getString("model"));
        }

        return planeIDs.toArray(new String[0]);
    }
    public String [] getAllClassesForComboBox()
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> classeNames = new ArrayList<>();
        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT name FROM class ORDER BY class_id DESC");

        while(data.next()) {
            classeNames.add(data.getString("name"));
        }
        return classeNames.toArray(new String[0]);
    }
    public String [] getAllAirportsForComboBox()
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> airportCodes = new ArrayList<>();
        Statement statement = SingletonConnection.getInstance().createStatement();
        ResultSet data = statement.executeQuery("SELECT * FROM airport ORDER BY code");

        while(data.next()) {
            airportCodes.add(data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country"));
        }

        return airportCodes.toArray(new String[0]);
    }
    public String [] getAllTerminalsOfAnAirportForComboBox(String airportId)
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> terminalsOfAnAirport = new ArrayList<>();
        String sql =
            "SELECT DISTINCT " +
                "terminal " +
            "FROM " +
                "gate gat, " +
                "airport air " +
            "WHERE " +
                "gat.airport = air.code AND " +
                "air.code = ? " +
            "ORDER BY " +
                "terminal;";

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, airportId);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {
            terminalsOfAnAirport.add(data.getString("terminal"));
        }

        return terminalsOfAnAirport.toArray(new String[0]);
    }
    public String [] getAllGatesOfAnAirportAndTerminalForComboBox(String airportId, String terminalId)
            throws SQLException, DataBaseConnectionException {
        ArrayList<String> gatesOfAnAirportAndTerminal = new ArrayList<>();
        String sql =
            "SELECT " +
                "number " +
            "FROM " +
                "gate gat, " +
                "airport air " +
            "WHERE " +
                "gat.airport = air.code AND " +
                "air.code = ? AND " +
                "gat.terminal = ? " +
            "ORDER BY " +
                "number;";

        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, airportId);
        preparedStatement.setString(2, terminalId);

        ResultSet data = preparedStatement.executeQuery();

        while (data.next()) {
            gatesOfAnAirportAndTerminal.add(Integer.toString(data.getInt("number")));
        }

        return gatesOfAnAirportAndTerminal.toArray(new String[0]);
    }
    public String getAirportToString(String gateId)
            throws SQLException, DataBaseConnectionException {
        String airportId;
        String sql =
            "SELECT " +
                "air.code, " +
                "air.name, " +
                "air.country " +
            "FROM " +
                "gate gat, " +
                "airport air " +
            "WHERE " +
                "gat.airport = air.code AND " +
                "gat.gate_id = ?;";
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, gateId);

        ResultSet data = preparedStatement.executeQuery();

        airportId = data.getString("code") + " - " + data.getString("name") + ", " + data.getString("country");

        return airportId;
    }
    //endregion

    //region Edit
    public void addFlight(Flight flightToAdd)
            throws SQLException, DataBaseConnectionException {
        String sql =
                "INSERT INTO " +
                    "flight " +
                "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToAdd);

        preparedStatement.executeUpdate();
    }
    public void modifyFlight(Flight flightToUpdate, String originalNumber)
            throws SQLException, DataBaseConnectionException {
        String sql =
            "UPDATE " +
                "flight " +
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
            "WHERE " +
                "number = ?";

        PreparedStatement preparedStatement = preparedFlightStatement(sql, flightToUpdate);
        preparedStatement.setString(10, originalNumber);

        preparedStatement.executeUpdate();
    }
    @Override
    public void modifyFlight(Flight flightToUpdate)
            throws SQLException, DataBaseConnectionException {
        modifyFlight(flightToUpdate, flightToUpdate.getNumber());
    }
    public void deleteFlight(String flightNumberToDelete)
            throws SQLException, DataBaseConnectionException {
        String sql =
                "DELETE " +
                "FROM " +
                    "flight " +
                "WHERE " +
                    "number = ?";
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);
        preparedStatement.setString(1, flightNumberToDelete);

        preparedStatement.executeUpdate();
    }
    //endregion

    //region Connection
    public void closeConnection()
            throws DataBaseCloseException {
        SingletonConnection.closeConnection();
    }
    //endregion

    //region Tools
    private static PreparedStatement preparedFlightStatement(String sql, Flight flight) throws SQLException, DataBaseConnectionException {
        PreparedStatement preparedStatement = SingletonConnection.getInstance().prepareStatement(sql);

        preparedStatement.setString(1,  flight.getNumber());
        preparedStatement.setTimestamp(2, new java.sql.Timestamp(flight.getDepartureTime().getTimeInMillis()));
        preparedStatement.setTimestamp(3, new java.sql.Timestamp(flight.getArrivalTime().getTimeInMillis()));
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
