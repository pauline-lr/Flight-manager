package dataAccesPackage;

import java.sql.*;

public class SingletonConnexion {
    private static Connection uniqueConnexion;

    public static Connection getInstance(){
        if (uniqueConnexion == null) {
            try {
                Connection connection =
                        DriverManager.getConnection("jdbc:mysql://localhost:3306/flight",
                                "root",
                                "B0ni1999") ;

                String requeteSQL = "select * from Etudiant";
                PreparedStatement prepStat = connection.prepareStatement(requeteSQL);
                ResultSet donnees = prepStat.executeQuery();
                while(donnees.next()){
                    System.out.println(donnees.getInt("Numéro") + " "
                            +donnees.getDate("Départ") + " "
                            +donnees.getString("Repas à bord") + " "
                            +donnees.getString("Description repas")
                            +donnees.getString("Aéroport de départ")
                            +donnees.getString("Aéroport d'arrivée")
                            +donnees.getString("Pilote")
                            +donnees.getString("Avion"));
                }
                System.out.println("Command successful");
            } catch (SQLException exception) {
                System.out.println("Error : " + exception.getMessage());
            }
        }
        return uniqueConnexion;
    }
}

