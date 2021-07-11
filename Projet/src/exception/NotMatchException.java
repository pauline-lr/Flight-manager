package exception;

public class NotMatchException extends Exception{
    private String objet;
    private String structure;

    public NotMatchException(String objet, String structure) {
        this.objet = objet;
        this.structure = structure;
    }

    public NotMatchException(String objet) {
        this.objet = objet;
    }

    @Override
    public String getMessage() {
        return objet + " ne correspond pas à la structure requise.\n" + structure;
    }
}
