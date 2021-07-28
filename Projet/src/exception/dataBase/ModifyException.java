package exception.dataBase;

public class ModifyException extends Exception{
    private String message;

    public ModifyException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la modification :\n" + message;
    }
}
