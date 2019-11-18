package pl.coderslab.balanceYourDiet.exception;

public class ProductPortionNotFoundException extends RuntimeException {

    public ProductPortionNotFoundException() {
        super("Product portion was not found");
    }
}
