package dataAccess;

import exception.dataBase.DataBaseCloseException;
import exception.dataBase.DataBaseConnectionException;

import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws DataBaseConnectionException {
        if (uniqueConnection == null) {
            try {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "LoréaSmith");
            }
            catch (SQLException exception) {
                throw new DataBaseConnectionException();
            }
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws DataBaseCloseException {
        if (uniqueConnection != null) {
            try {
                uniqueConnection.close();
            } catch (SQLException exception){
                throw new DataBaseCloseException();
            }
        }
    }
}
