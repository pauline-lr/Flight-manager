package exception;

import java.util.GregorianCalendar;


public class BirthDateException extends Exception{
    private GregorianCalendar wrongBirthDate;

    public BirthDateException (GregorianCalendar wrongBirthDate) {
        this.wrongBirthDate = wrongBirthDate;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongBirthDate + " value for the birth date is invalid !"
                + "\nThe date cannot be later than today";
    }
}
