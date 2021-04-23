package dataAccess;

import exception.AllFlightsException;
import model.Flight;
import model.Pilot;

import java.sql.*;
import java.util.ArrayList;

public class FlightDBAccess {
    public void getAllPilots() throws AllFlightsException, SQLException {
        Connection connection = SingletonConnection.getInstance();

        String sql = "SELECT * FROM pilot";

        Statement statement = connection.createStatement();
        ResultSet allPilots = statement.executeQuery(sql);


        while (allPilots.next()) {
            System.out.println(allPilots.getString("first_name") + " " + allPilots.getString("last_name"));
        }
        connection.close();
    }
}
