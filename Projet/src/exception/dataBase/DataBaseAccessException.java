package exception.dataBase;

public class DataBaseAccessException extends Exception{
    public String getMessage() {
        return "Une erreur s'est produite aves nos serveurs\n" +
                "Veuillez réessayer plus tard.\n"+
                "Nous nous excusons pour le désagrément.";
    }
}
