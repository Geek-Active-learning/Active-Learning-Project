package activelearning.com.DL.services;

import activelearning.com.BL.entities.User;
import activelearning.com.SHARED.custom.UserAlreadyRegisteredException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import activelearning.com.DL.constants.Messages;
import activelearning.com.DL.repository.UserRepository;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return  userRepository.findAll();
    }

    public Optional<User> authenticate(User user){
        if(user.getEmail().equals(null)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format(Messages.EMAIL_IS_EMPTY));
        }

        if(user.getPassword().equals(null)){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format(Messages.PASSWORD_IS_EMPTY));
        }

        if(!isEmailInUse(user.getEmail())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format(Messages.EMAIL_DOES_NOT_EXIST));
        }

        Optional<User> userToAuthenticate = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(!userToAuthenticate.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format(Messages.USER_DOES_NOT_EXIST_MESSAGE, user.toString()));
        }
        return  userToAuthenticate;
    }

    public Optional<User> getUserById(@NonNull Long userId){
         return userRepository.findById(userId);
    }

    public User addUser(User user) throws UserAlreadyRegisteredException {

        if(isPhoneNumberInUse(user.getPhoneNumber())){
            throw new UserAlreadyRegisteredException(
                    String.format(Messages.PHONE_NUMBER_ALREADY_EXIST, user.getPhoneNumber()));
        }

        if(user.getPhoneNumber().equals(null)){
            throw new UserAlreadyRegisteredException(
                    String.format(Messages.PHONE_NUMBER_IS_NULL, user.getPhoneNumber()));
        }

        if(isEmailInUse(user.getEmail())){
            throw new UserAlreadyRegisteredException(
                    String.format(Messages.EMAIL_ALREADY_EXIST, user.getPhoneNumber()));
        }

        if(isGithubUsernameInUse(user.getGithub())){
            throw new UserAlreadyRegisteredException(
                    String.format(Messages.GITHUB_USERNAME_ALREADY_EXIST, user.getPhoneNumber()));
        }
        userRepository.save(buildUser(user));
        return user;
    }
    public  User buildUser(@NonNull User user){
        return User.builder()
                .dob(user.getDob())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .surname(user.getSurname())
                .startDate(user.getStartDate())
                .github(user.getGithub()).build();
    }

    @Transactional
    public ResponseEntity<String> updateUser(User user,Long userId){
        //TODO
        return null;
    }

    public ResponseEntity<String> deleteUser(@NonNull Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY, String.format(Messages.USER_NOT_DELETED_ERROR_MESSAGE, userId));
        }
        userRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(String.format(Messages.USER_DELETED_SUCCESSFUL_MESSAGE, user.toString()));
    }

    public boolean isEmailInUse(@NonNull String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isPhoneNumberInUse(@NonNull String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    public boolean isGithubUsernameInUse(@NonNull String githubUsername){
        return userRepository.findByGithub(githubUsername).isPresent();
    }
}
