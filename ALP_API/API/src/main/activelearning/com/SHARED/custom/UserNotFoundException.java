package activelearning.com.SHARED.custom;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String message) {
            super(message);
        }
}
