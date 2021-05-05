package exception;

public class DepartureDateException extends Exception{
    private String wrongDepartureDate;

    public DepartureDateException (String wrongDepartureDate) {
        this.wrongDepartureDate = wrongDepartureDate;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongDepartureDate + " value for the departure date is invalid !";
    }

}
