package exception;

public class NumberGateException extends Exception{
    private int wrongNumberGate;

    public NumberGateException (int wrongNumberGate) {
        this.wrongNumberGate = wrongNumberGate;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongNumberGate + " value for description of meal is invalid !";
    }
}
