package exception;

public class FlightException extends Exception {
    public static class NumberFlightException extends Exception {
        private String wrongNumberFlight;

        public NumberFlightException(String wrongNumberFlight) {
            this.wrongNumberFlight = wrongNumberFlight;
        }

        public String getMessage() {
            if (wrongNumberFlight.isEmpty()) {
                return "Le numéro du vol est vide (champs obligatoire)";
            } else {
                return "Le numéro de vol \"" + wrongNumberFlight + "\" ne correspond pas à la structure requise.\n" +
                        "Un numéro de vol se compose de 2 lettres majuscules et de 4 chiffres.";
            }
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
