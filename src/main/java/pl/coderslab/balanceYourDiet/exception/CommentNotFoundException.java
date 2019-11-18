package pl.coderslab.balanceYourDiet.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("Comment was not found");
    }
}
