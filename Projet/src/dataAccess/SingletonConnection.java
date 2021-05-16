package dataAccess;

import exception.DataBaseCloseException;
import exception.DataBaseConnectionException;

import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws DataBaseConnectionException {
        if (uniqueConnection == null) {
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "B0ni1999");
            }
            catch (SQLException exception) {
                throw new DataBaseConnectionException();
            }
        }
        return uniqueConnection;
    }

    // il faut fermer la connexion à la base de donnée
    public static void closeConnection() throws DataBaseCloseException {
        try{
            if(uniqueConnection != null)
                uniqueConnection.close();
        }
        catch (SQLException exception){
            throw new DataBaseCloseException();
        }
    }
}
