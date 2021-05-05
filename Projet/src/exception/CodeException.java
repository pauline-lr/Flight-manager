package exception;

public class CodeException extends Exception {
    private String wrongCode;

    public CodeException(String wrongCode) {
        this.wrongCode = wrongCode;
    }

    public String getMessage() {
        return "The proposed " + wrongCode + " value for code is invalid !";
    }
}
