package pl.coderslab.balanceYourDiet.exception;

public class MealNotFoundException extends RuntimeException {

    public MealNotFoundException() {
        super("Meal was not found");
    }
}
