package activelearning.com.SHARED.custom;

public class PasswordTooWeakException extends RuntimeException {
    public PasswordTooWeakException(final String message) {
        super(message);
    }
}
