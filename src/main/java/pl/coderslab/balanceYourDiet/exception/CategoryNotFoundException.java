package pl.coderslab.balanceYourDiet.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Category was not found");
    }

}
