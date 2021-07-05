package dataAccess;

import java.io.IOException;
import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws IOException, SQLException {
        if (uniqueConnection == null) {
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "LoréaSmith");
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws SQLException, IOException  {
        if (uniqueConnection != null) {
                uniqueConnection.close();
        }
    }
}
