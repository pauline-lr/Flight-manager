package exception;

public class DBConnectionException extends Exception {
    public String getMessage(){
        return "ERROR : Connection to the database failed";
    }
}
