package exception;

public class AddException extends Exception{
    private String message;

    public AddException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur d'ajout dans la base de données :\n" + message;
    }
}
