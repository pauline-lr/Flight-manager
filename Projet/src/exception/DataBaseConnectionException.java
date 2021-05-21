package exception;

public class DataBaseConnectionException extends Exception {
    public String getMessage(){
        return "Erreur de connexion à la base de donnée.\nVeuillez réessayer plus tard";
    }
}
