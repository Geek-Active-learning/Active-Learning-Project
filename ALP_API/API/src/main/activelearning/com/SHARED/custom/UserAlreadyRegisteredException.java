package activelearning.com.SHARED.custom;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyRegisteredException  extends AuthenticationException {
    public UserAlreadyRegisteredException(final String message) {
        super(message);
    }
}
