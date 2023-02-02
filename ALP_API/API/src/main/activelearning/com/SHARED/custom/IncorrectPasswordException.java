package activelearning.com.SHARED.custom;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(final String message) {
        super(message);
    }
}