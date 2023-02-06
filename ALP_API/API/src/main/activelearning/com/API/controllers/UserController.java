package activelearning.com.API.controllers;

import activelearning.com.BL.entities.User;
import activelearning.com.DL.services.UserService;
import activelearning.com.SHARED.custom.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static activelearning.com.DL.constants.Messages.USER_NOT_FOUND_ERROR_MESSAGE;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
 public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/authenticate")
    public Optional<User> authenticate(@RequestBody User user) {
        return this.userService.authenticate(user);
    }

    @PutMapping("/activate/{userid}")
    public Optional<User> activateUser(@PathVariable Long userId) {
        //TODO
        return this.userService.getUserById(userId);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public User getUserById(@PathVariable Long userId) {
        return userService
                .getUserById(userId)
                .orElseThrow(
                        () ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND, String.format(USER_NOT_FOUND_ERROR_MESSAGE, userId)));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        try {
            this.userService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("User created");
        } catch (UserAlreadyRegisteredException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long userId) {
        return this.userService.updateUser(user, userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return this.userService.deleteUser(userId);
    }
}
