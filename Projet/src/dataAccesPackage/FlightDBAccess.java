package dataAccesPackage;

import exceptionPackage.AllFlightException;
import modelPackage.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FlightDBAccess {
    public ArrayList<Flight> getAllFlight( ) throws AllFlightException, SQLException {
        // Essayer d’accéder à la base de données via
        Connection connection = SingletonConnexion.getInstance();
        // Essayer de lire les livres dans la table Book
        // Créer et retourner une ArrayList de livres
        String sql = " select Flight where num = ? and flightTime = ? and departureTime = ? " +
                "and isMealOnBoard = ? and mealDescription = ? and departureAirportID = ?  " +
                "and arrivalAirportID = ? and pilotId = ? and planeID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        for (Flight flight : getAllFlight()) {
            statement.setString(1, flight.getNumber());

            // attention facultatif
            statement.setInt(2, flight.getFlightTime());

            java.sql.Date sqlDate =  new java.sql.Date(flight.getDepartureTime().getTimeInMillis());
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(sqlDate);
            statement.setDate(3, sqlDate);

            statement.setBoolean(4,flight.getMealOnBoard());

            // attention facultatif
            statement.setString(5,flight.getMealDescription());

            statement.setString(6, flight.getDepartureAirportID());

            statement.setString(7, flight.getArrivalAirportID());

            statement.setString(8, flight.getPilotID());

            statement.setString(9, flight.getPlaneID());
        }


        connection.close( );
        //Contient la requête SQL à exécuter
        return null;
    }
}
