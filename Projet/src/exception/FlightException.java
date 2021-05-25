package exception;

public class FlightException extends Exception {
    public static class TerminalException extends Exception {
        private char wrongTerminal;

        public TerminalException(char wrongTerminal) {
            this.wrongTerminal = wrongTerminal;
        }

        public String getMessage() {
            return "The proposed " + wrongTerminal + " value for a Terminal is invalid !";
        }
    }

    public static class NumberGateException extends Exception {
        private int wrongNumberGate;

        public NumberGateException(int wrongNumberGate) {
            this.wrongNumberGate = wrongNumberGate;
        }

        public String getMessage() {
            return "The proposed " + wrongNumberGate + " value for description of meal is invalid !";
        }
    }

    public static class NumberFlightException extends Exception {
        private String wrongNumberFlight;

        public NumberFlightException(String wrongNumberFlight) {
            this.wrongNumberFlight = wrongNumberFlight;
        }

        public String getMessage() {
            if (wrongNumberFlight.isEmpty()) {
                return "Le numéro du vol est vide (champs obligatoire)";
                //}else if(/*se trouve dans la DB*/){
                //return "Le numéro de vol existe déjà";
            } else {
                return "Le numéro de vol \"" + wrongNumberFlight + "\" ne correspond pas à la structure requise.\n"
                        + "Un numéro de vol se compose de 2 lettres majuscule et de 4 chiffres.";
            }
        }
    }

    public static class MealDescriptionException extends Exception {
        public String getMessage() {
            return "La description de repas est trop longue.";
        }
    }

    public static class DepartureDateException extends Exception {
        public String getMessage() {
            return "La date de départ doit être ultérieure à date d'aujourd'hui.";
        }

    }

    public static class ArrivalDateException extends Exception {
        public String getMessage() {
            return "La date d'arrivée doit être ultérieure à la date de départ.";
        }

    }
}
