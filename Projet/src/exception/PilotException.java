package exception;

import java.util.GregorianCalendar;

public class PilotException extends Exception{
    public static class LicenceNumberException extends Exception{
        private String wrongLicenseNumber;

        public LicenceNumberException(String wrongLicenseNumber) {
            this.wrongLicenseNumber = wrongLicenseNumber;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongLicenseNumber + " value for a licence number is invalid !";
        }
    }

    public static class FlyingFlightException extends Exception{
        private GregorianCalendar wrongTime;

        public FlyingFlightException(GregorianCalendar wrongTime) {
            this.wrongTime = wrongTime;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongTime + " value for a flying time is invalid !";
        }
    }
}
