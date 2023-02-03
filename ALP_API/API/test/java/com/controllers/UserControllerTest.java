package com.controllers;

import activelearning.com.API.controllers.UserController;
import activelearning.com.BL.entities.User;
import activelearning.com.DL.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {UserService.class, PasswordEncoder.class})
@ActiveProfiles("test")
public class UserControllerTest {

    @MockBean
    UserService userService;
    private final UserController mockUserController;
    private final UserService mockedUserService;
    private final PasswordEncoder mockPasswordEncoder;

    private final User userBuilds =
            User.builder()
                    .id(34534L)
                    .email("test@gmail.com")
                    .name("test")
                    .dob(new Date())
                    .startDate(new Date())
                    .phoneNumber("0661826809")
                    .surname("testSurname")
                    .github("testGithub")
                    .password("somePass").build();

    UserControllerTest() {
        this.mockedUserService = mock(UserService.class);
        this.mockPasswordEncoder = mock(PasswordEncoder.class);
        this.mockUserController = new UserController(mockedUserService);
    }

    @Test
    void getAllUsers_returnsAllUsers() {
        when(mockedUserService.getUsers()).thenReturn(List.of(userBuilds));
        assertThat(mockUserController.getAllUsers()).isEqualTo(List.of(userBuilds));
    }

    @Test
    public void getUserById_returnCorrectUser() {
        final Long userId =34534L;
        when(mockedUserService.getUserById(userId)).thenReturn(Optional.ofNullable(userBuilds));
        assertThat(mockUserController.getUserById(userId)).isEqualTo(userBuilds);
    }

    @Test
    public void getUserById_returnUserNotFound_whenIsNotPresent() {
        when(mockedUserService.getUserById(any(Long.class))).thenReturn(Optional.empty());
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> mockUserController.getUserById(0L));
    }

}
