package exception;

import java.util.GregorianCalendar;

public class FlightException extends Exception {
    public static class TerminalException extends Exception{
        private char wrongTerminal;

        public TerminalException (char wrongTerminal) {
            this.wrongTerminal = wrongTerminal;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongTerminal + " value for a Terminal is invalid !";
        }
    }

    public static class NumberGateException extends Exception{
        private int wrongNumberGate;

        public NumberGateException (int wrongNumberGate) {
            this.wrongNumberGate = wrongNumberGate;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongNumberGate + " value for description of meal is invalid !";
        }
    }

    public static class NumberFlightException extends Exception{
        private String wrongNumberFlight;

        public NumberFlightException (String wrongNumberFlight) {
            this.wrongNumberFlight = wrongNumberFlight;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongNumberFlight + " value for number of a Flight is invalid !";
        }
    }

    public static class MealDescriptionException extends Exception{
        private String wrongDescriptionMeal;

        public MealDescriptionException (String wrongDescriptionMeal) {
            this.wrongDescriptionMeal = wrongDescriptionMeal;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongDescriptionMeal + " value for description of meal is invalid !";
        }
    }

    public static class DepartureDateException extends Exception{
        private GregorianCalendar wrongDepartureDate;

        public DepartureDateException (GregorianCalendar wrongDepartureDate) {
            this.wrongDepartureDate = wrongDepartureDate;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongDepartureDate + " value for the departure date is invalid !";
        }

    }

    public static class ArrivalDateException extends Exception{
        private GregorianCalendar wrongArrivalDate;

        public ArrivalDateException (GregorianCalendar wrongArrivalDate) {
            this.wrongArrivalDate = wrongArrivalDate;
        }

        public String getMessage( ) {
            return  "The proposed " + wrongArrivalDate + " value for the arrival date is invalid !";
        }

    }
}
