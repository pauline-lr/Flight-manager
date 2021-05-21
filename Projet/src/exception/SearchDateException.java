package exception;

public class SearchDateException extends Exception{
    public String getMessage(){
        return "Veuillez entrer une première date antérieure à l'autre ";
    }

}
