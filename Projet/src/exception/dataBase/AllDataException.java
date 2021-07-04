package exception.dataBase;

public class AllDataException extends Exception{
    private String exceptionMessage;

    public AllDataException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return "Erreur de récupération dans la base de donnée :\n" + exceptionMessage;
    }
}
