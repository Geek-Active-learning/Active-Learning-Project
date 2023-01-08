package src.main.java.com.API.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.main.java.com.BL.entities.User;
import src.main.java.com.DL.services.UserService;

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
    public ResponseEntity<String> createNewUser(@RequestBody User user){
        System.out.println(user);
        return this.userService.createNewUser(user);
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
