package exception.dataBase;

public class AddDataException extends Exception{
    private String message;

    public AddDataException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur d'ajout dans la base de données :\n" + message;
    }
}
