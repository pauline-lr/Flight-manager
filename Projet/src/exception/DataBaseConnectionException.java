package exception;

public class DataBaseConnectionException extends Exception {
    public String getMessage(){
        return "ERROR : Connection to the database failed";
    }
}
