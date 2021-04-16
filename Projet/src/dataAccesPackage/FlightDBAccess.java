package dataAccesPackage;

import exceptionPackage.AllFlightException;
import modelPackage.Flight;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightDBAccess {
    private Flight flight;

    public ArrayList<Flight> getAllBooks( ) throws AllFlightException, SQLException {
        // Essayer d’accéder à la base de données via
        Connection connection = SingletonConnexion.getInstance();
        // Essayer de lire les livres dans la table Book
        // Créer et retourner une ArrayList de livres
        String sql = " select Flight where num = ? and flightTime = ? and departureTime = ? " +
                "and isMealOnBoard = ? and mealDescription = ? and departureAirportID = ?  " +
                "and arrivalAirportID = ? and pilotId = ? and planeID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Numéro");
        statement.setInt(2, 60);
        java.sql.Date sqlDate =  new java.sql.Date(flight.getDepartureTime().getTimeInMillis());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sqlDate);
        statement.setDate(3, sqlDate);
        statement.setBoolean(4,true);
        // etc

        //Contient la requête SQL à exécuter
        return null;
    }
}
