package exception;

import java.util.GregorianCalendar;

public class PassengerException extends Exception{
    public static class PassportNumberException extends Exception{
        private String wrongPassportNumber;

        public PassportNumberException(String wrongPassportNumber) {
            this.wrongPassportNumber = wrongPassportNumber;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongPassportNumber + " value for a paspport number is invalid !"
                    + "\nExactly 9 characters are needed";
        }
    }

    public static class BirthDateException extends Exception{
        private GregorianCalendar wrongBirthDate;

        public BirthDateException (GregorianCalendar wrongBirthDate) {
            this.wrongBirthDate = wrongBirthDate;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongBirthDate + " value for the birth date is invalid !"
                    + "\nThe date cannot be later than today";
        }

    }
}
