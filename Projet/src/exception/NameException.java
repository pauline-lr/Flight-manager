package exception;

public class NameException extends Exception{
    private String wrongName;

    public NameException (String wrongName) {
        this.wrongName = wrongName;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongName + " value for a name of a airport is invalid !";
    }
}
