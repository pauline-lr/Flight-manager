package exception;

public class TerminalException extends Exception{
    private char wrongTerminal;

    public TerminalException (char wrongTerminal) {
        this.wrongTerminal = wrongTerminal;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongTerminal + " value for a Terminal is invalid !";
    }
}
