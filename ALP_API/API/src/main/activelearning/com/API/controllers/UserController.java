package activelearning.com.API.controllers;

import activelearning.com.SHARED.custom.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import activelearning.com.BL.entities.User;
import activelearning.com.DL.services.UserService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getUsers();
    }

    @PostMapping("/authenticate")
    public  Optional<User>  authenticate(@RequestBody User user){
        return this.userService.authenticate(user);
    }

    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId){
        return this.userService.getUserById(userId);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createNewUser(@RequestBody User user){
        try{
            this.userService.createNewUser(user);
            return ResponseEntity.status(HttpStatus.OK).body("User created");
        }catch (UserAlreadyRegisteredException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable Long userId){
        return this.userService.updateUser(user,userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        return this.userService.deleteUser(userId);
    }
}
