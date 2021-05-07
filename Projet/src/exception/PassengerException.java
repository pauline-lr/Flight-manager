package exception;

public class PassengerException extends Exception{
    public static class PassportNumberException extends Exception{
        private String wrongPassportNumber;

        public PassportNumberException(String wrongPassportNumber) {
            this.wrongPassportNumber = wrongPassportNumber;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongPassportNumber + " value for a paspport number is invalid !";
        }
    }
}
