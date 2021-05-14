package exception;

public class DataBaseCloseException extends Exception{
    public String getMessage(){
        return "ERROR : Failed to close the database";
    }
}
