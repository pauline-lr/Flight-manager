package exception;

public class DataBaseAccessException extends Exception{
    public String getMessage() {
        return "ERROR : connection";
    }
}
