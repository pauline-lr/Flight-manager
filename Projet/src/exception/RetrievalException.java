package exception;

public class RetrievalException extends Exception{
    private String exceptionMessage;

    public RetrievalException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return "Erreur de récupération dans la base de donnée :\n" + exceptionMessage;
    }
}
