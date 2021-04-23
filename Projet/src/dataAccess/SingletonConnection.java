package dataAccess;

import java.sql.*;

public class SingletonConnection {
    private static Connection uniqueConnection;
    public static Connection getInstance() {
        if (uniqueConnection == null) {
            try {
                Connection uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline", "root", "LoréaSmith");
            }
            catch (SQLException exception) {
                System.out.println("Erreur : " + exception.getMessage());
            }
        }
        return uniqueConnection;
    }
}
