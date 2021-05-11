package exception;

public class DBCloseException extends Exception{
    public String getMessage(){
        return "ERROR : Failed to close the database";
    }
}
