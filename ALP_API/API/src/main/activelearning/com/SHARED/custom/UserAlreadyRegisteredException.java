package activelearning.com.SHARED.custom;


import org.springframework.ldap.AuthenticationException;

public class UserAlreadyRegisteredException  extends RuntimeException {
    public UserAlreadyRegisteredException(final String message) {
        super(message);
    }
}
