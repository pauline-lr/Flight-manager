package exception;

public class ConnectionException extends Exception {
    private String exceptionMessage;

    public ConnectionException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage(){
        return "Erreur de connexion à la base de donnée.\nVeuillez réessayer plus tard";
    }
}
