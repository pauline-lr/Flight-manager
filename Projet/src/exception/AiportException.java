package exception;

public class AiportException extends Exception {

    public static class CodeException extends Exception {
        private String wrongCode;

        public CodeException(String wrongCode) {
            this.wrongCode = wrongCode;
        }

        public String getMessage() {
            return "The proposed " + wrongCode + " value for code is invalid !";
        }
    }

    public static class CountryException extends Exception{
        private String wrongCountry;

        public CountryException (String wrongCountry) {
            this.wrongCountry = wrongCountry;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongCountry + " value for a name of a country is invalid !";
        }
    }

    public static class NameAirportException extends Exception{
        private String wrongName;

        public NameAirportException(String wrongName) {
            this.wrongName = wrongName;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongName + " value for a name of a airport is invalid !";
        }
    }
}
