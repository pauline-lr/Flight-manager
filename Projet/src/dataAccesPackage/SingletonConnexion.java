package dataAccesPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection uniqueConnexion;

    public static Connection getInstance(){
        if (uniqueConnexion == null) {
            // Essayer de créer une connexion à la base de données
            try {
                Connection connection =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/flight",
                                "root",
                                "B0ni1999") ;
                System.out.println("ok pour la connection");
            }
            catch (SQLException exception) {
                System.out.println(exception.getMessage());
                System.out.println("donc pas ok");
            }
        }
        return uniqueConnexion;
    }
}

