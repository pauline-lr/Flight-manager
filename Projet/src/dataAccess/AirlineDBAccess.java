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

        return flights;
    }

    
}
