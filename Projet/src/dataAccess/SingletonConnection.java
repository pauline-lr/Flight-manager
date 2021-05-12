package dataAccess;

import exception.DBCloseException;
import exception.DBConnectionException;

import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws DBConnectionException {
        if (uniqueConnection == null) {
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "LoréaSmith");
            }
            catch (SQLException exception) {
                throw new DBConnectionException();
            }
        }
        return uniqueConnection;
    }

    // il faut fermer la connexion à la base de donnée
    public static void closeConnection() throws DBCloseException {
        try{
            if(uniqueConnection != null)
                uniqueConnection.close();
        }
        catch (SQLException exception){
            throw new DBCloseException();
        }
    }
}
