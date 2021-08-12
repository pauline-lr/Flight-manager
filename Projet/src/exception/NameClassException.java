package exception;

public class NameClassException extends Exception{
    private String wrongName;

    public NameClassException(String wrongName) {
        this.wrongName = wrongName;
    }

    public String getMessage( ) {
        return  "Le nom des classes ne peut être que \"Première\", \"Economique\" et \"Affaire\"\n"
                + "\"" + wrongName + "\" n'est pas accepté";
    }
}
