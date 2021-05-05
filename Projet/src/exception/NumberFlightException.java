package exception;

public class NumberFlightException extends Exception{
    private String wrongNumberFlight;

    public NumberFlightException (String wrongNumberFlight) {
        this.wrongNumberFlight = wrongNumberFlight;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongNumberFlight + " value for number of a Flight is invalid !";
    }

    /*Code setter
        String numberFlight = String.valueOf(numberTextField);

        public void setNumberTextField(String numberFlight) throws NumberFlightException {
            String patternNumberFlight = "\\b[A-z][A-z]\\d{4}$";
            Pattern r = Pattern.compile(patternNumberFlight);
            Matcher m = r.matcher(numberFlight);
            if (m.find())
                System.out.println("MATCH");
                // ajout à la BD
            else
                throw new NumberFlightException(numberFlight);
        }
        //------------------
    * */
}
