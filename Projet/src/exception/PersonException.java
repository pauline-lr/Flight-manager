package exception;

public class PersonException extends Exception{
    public static class FirstNameException extends Exception{
        private String wrongName;

        public FirstNameException(String wrongName) {
            this.wrongName = wrongName;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongName + " value for a first name is invalid !";
        }
    }

    public static class LastNameException extends Exception{
        private String wrongName;

        public LastNameException(String wrongName) {
            this.wrongName = wrongName;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongName + " value for a first name is invalid !";
        }
    }

    public static class PhoneNumberException extends Exception{
        private String wrongPhoneNumber;

        public PhoneNumberException(String wrongPhoneNumber) {
            this.wrongPhoneNumber = wrongPhoneNumber;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongPhoneNumber + " value for a phone number is invalid !";
        }
    }

    public static class EmailException extends Exception{
        private String wrongEmail;

        public EmailException(String wrongEmail) {
            this.wrongEmail = wrongEmail;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongEmail + " value for an email is invalid !";
        }
    }
}
