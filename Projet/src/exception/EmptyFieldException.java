package exception;

public class EmptyFieldException extends Exception{
    private String objet;

    public EmptyFieldException(String message, String objet) {
        this.objet = objet;
    }


    public String getMessage() {
            return " Le champs " + objet + " ne peut pas être vide.";
        }

}
