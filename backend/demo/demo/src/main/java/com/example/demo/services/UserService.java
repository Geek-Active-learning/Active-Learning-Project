package com.example.demo.services;

import com.example.demo.constants.Messages;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public Optional<User> getUserById(@NonNull Long userId){
        Optional<User>  user =userRepository.findById(userId);

        if(user.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format(Messages.USER_NOT_FOUND_ERROR_MESSAGE, userId));
        }
        return user;
    }

    public void createNewUser( User user){
        if(isPhoneNumberInUse(user.getPhoneNumber())){
            throw new IllegalArgumentException("Phone number already exists");
        }

        if(isEmailInUse(user.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
     User user1 = buildUser(user);
        userRepository.save(user1);
    }
    public  User buildUser(@NonNull User user){
        return User.builder()
                .dob(user.getDob())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .phoneNumber(user.getPhoneNumber())
                .stream(user.getStream())
                .surname(user.getSurname())
                .startDate(user.getStartDate())
                .username(user.getName()+" "+user.getSurname()).build();
    }

    @Transactional
    public Optional<User> updateUser(){
        return null;
    }

    public ResponseEntity<String> deleteUser(@NonNull Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(String.format(Messages.USER_DELETED_SUCCESSFUL_MESSAGE, user.toString()));
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_GATEWAY, String.format(Messages.USER_NOT_DELETED_ERROR_MESSAGE, userId));
    }

    public boolean isEmailInUse(@NonNull String email){
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public boolean isPhoneNumberInUse(@NonNull String phoneNumber){
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return user.isPresent();
    }
}
