package exception.dataBase;

public class DataBaseCloseException extends Exception{
    private String message;

    public DataBaseCloseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return "Nos serveur ne répondent pas.\nVeuillez réessayer plus tard.";
    }
}
