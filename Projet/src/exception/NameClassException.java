package exception;

public class NameClassException extends Exception{
    private String wrongName;

    public NameClassException(String wrongName) {
        this.wrongName = wrongName;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongName + " value for a name of a airport is invalid !";
    }
}
