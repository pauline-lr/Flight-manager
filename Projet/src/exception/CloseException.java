package exception;

public class CloseException extends Exception{
    private String message;

    public CloseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return "Nos serveur ne répondent pas.\nVeuillez réessayer plus tard.\n" + message;
    }
}
