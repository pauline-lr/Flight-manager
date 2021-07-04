package exception.dataBase;

public class DataBaseConnectionException extends Exception {
    private String exceptionMessage;

    public DataBaseConnectionException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage(){
        return "Erreur de connexion à la base de donnée.\nVeuillez réessayer plus tard";
    }
}
