package dataAcces;

import exception.AllFlightException;
import model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;


public class FlightDBAccess {
    private PreparedStatement statement;
    public ArrayList<Flight> getAllFlight( ) throws SQLException, AllFlightException {
        // Essayer d’accéder à la base de données via
        Connection connection = SingletonConnexion.getInstance();
        // Essayer de lire les livres dans la table Book


        // Créer et retourner une ArrayList de livres
        String sql = " select Flight where num = ? and flightTime = ? and departureTime = ? " +
                "and isMealOnBoard = ? and mealDescription = ? and departureAirportID = ?  " +
                "and arrivalAirportID = ? and pilotId = ? and planeID = ?";

        statement = connection.prepareStatement(sql);

        int nbUpdatedLines = addFlights();

        connection.close( );

        //Contient la requête SQL à exécuter
        return getAllFlight();
    }

    public int addFlights() throws AllFlightException, SQLException {
        for (Flight flight : getAllFlight()) {
            statement.setString(1, flight.getNumber());

            // attention facultatif
            //statement.setInt(2, flight.getFlightTime());

            java.sql.Date sqlDate = new java.sql.Date(flight.getDepartureTime().getTimeInMillis());
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(sqlDate);
            statement.setDate(2, sqlDate);

            statement.setBoolean(3, flight.getMealOnBoard());

            // attention facultatif
            //statement.setString(5, flight.getMealDescription());

            statement.setString(4, flight.getDepartureAirportID());

            statement.setString(5, flight.getArrivalAirportID());

            statement.setString(6, flight.getPilotID());

            statement.setString(7, flight.getPlaneID());

            //allFlight(flight);
        }
        // Exécuter + Récupérer le nombre de lignes modifiées
        return statement.executeUpdate();
    }

    /*public void allFlight(Flight flight){
        // Exécuter le select sur la table book
        String sqlInstruction = "select * from book";
        statement = connection.prepareStatement(sqlInstruction);
        ResultSet data = statement.executeQuery();

// Boucler sur toutes les lignes du ResultSet
        while (data.next()) {
// Traiter la ligne courante du ResultSet :
// créer un objet de type Book à partir des colonnes obligatoires
            flight = new flight(data.getString("isbn"));
// Si le nombre de pages n’est pas null en BD

            modifier l’objet book
// Essayer de lire le nombre de pages de la ligne courante
            pages = data.getInt("pages");
// Tester si la dernière valeur lue du ResultSet n’est pas nulle
            if (!data.wasNull()) {
                book.setPages(pages);
            }
    …      // Faire de même avec chaque colonne facultative
            allBooks.add(book);
        }*/
}
