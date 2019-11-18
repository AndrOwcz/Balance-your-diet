package pl.coderslab.balanceYourDiet.exception;

public class PlanNotFoundException extends RuntimeException {

    public PlanNotFoundException() {
        super("Plan was not found");
    }
}
