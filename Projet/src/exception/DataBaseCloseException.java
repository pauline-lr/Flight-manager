package exception;

public class DataBaseCloseException extends Exception{
    public String getMessage(){
        return "Nos serveur ne répondent pas.\nVeuillez réessayer plus tard.";
    }
}
