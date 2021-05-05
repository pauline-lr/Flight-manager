package exception;

public class MealDescriptionException extends Exception{
    private String wrongDescriptionMeal;

    public MealDescriptionException (String wrongDescriptionMeal) {
        this.wrongDescriptionMeal = wrongDescriptionMeal;
    }

    public String getMessage( ) {
        return  "The proposed " + wrongDescriptionMeal + " value for description of meal is invalid !";
    }
}
