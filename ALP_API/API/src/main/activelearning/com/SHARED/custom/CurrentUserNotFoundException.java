package activelearning.com.SHARED.custom;

public class CurrentUserNotFoundException extends RuntimeException {
    public CurrentUserNotFoundException(final String message) {
        super(message);
    }
}