package exception;

public class DataAccessException extends Exception{
    public String getMessage() {
        return "ERROR : connection";
    }
}
