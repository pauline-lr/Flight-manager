package exception;

public class TextLengthException extends Exception{
    private String message;

    public TextLengthException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur de longueur de champs :\n" + message;
    }
}
