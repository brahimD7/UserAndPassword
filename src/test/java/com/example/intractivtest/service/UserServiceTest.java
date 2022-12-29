package com.example.intractivtest.service;

import com.example.intractivtest.dto.Compliance;
import com.example.intractivtest.dto.Password;
import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.entity.User;
import com.example.intractivtest.execption.InvalidPasswordException;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserNotFoundException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import com.example.intractivtest.mapper.UserMapper;
import com.example.intractivtest.repository.UserRepository;
import com.example.intractivtest.util.UserTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String PASSWORD_NOT_COMPLIANT = "passwordNotCompliant";
    private static final String PASSWORD_COMPLIANT = "PasswordCompliant77!";

    @Mock
    private UserRepository userRepository;

    @Mock
    private ComplianceService complianceService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;


    @Test
    void registerUser_Ok() {
        UserDTO userDTO = UserTestUtil.getValidUserDTO();

        when(userRepository.findUserByName(userDTO.getName()))
                .thenReturn(Optional.empty());

        when(complianceService.getPasswordCompliance(userDTO.getPassword()))
                .thenReturn(Compliance.builder().isValid(true).reason("").build());

        when(userMapper.mapUserDtoToUser(any(UserDTO.class))).thenReturn(new User());

        assertDoesNotThrow(() -> userService.registerUser(userDTO));
    }

    @Test
    void registerUser_AlreadyExists() {
        UserDTO userDTO = UserTestUtil.getValidUserDTO();

        when(userRepository.findUserByName(userDTO.getName()))
                .thenReturn(Optional.of(new User()));

        UserAlreadyExistsException exception = Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            userService.registerUser(userDTO);
        });

        Assertions.assertEquals(String.format("user %s already exists",userDTO.getName()), exception.getMessage());
    }

    @Test
    void registerUser_PasswordNotCompliant() {
        UserDTO userDTO = UserTestUtil.getValidUserDTO();
        userDTO.setPassword(PASSWORD_NOT_COMPLIANT);

        when(userRepository.findUserByName(userDTO.getName()))
                .thenReturn(Optional.empty());

        when(complianceService.getPasswordCompliance(userDTO.getPassword()))
                .thenReturn(Compliance.builder().isValid(false).reason("some reason").build());

        Assertions.assertThrows(UserPasswordNotCompliantException.class, () -> {
            userService.registerUser(userDTO);
        });
    }


    @Test
    void login_Ok() {

        String login = "jojo";
        Password password= Password.builder().password(PASSWORD_COMPLIANT).build();

        when(userRepository.findUserByNameAndPassword(login, password.getPassword()))
                .thenReturn(Optional.of(new User()));

        assertDoesNotThrow(() -> userService.login(login,password));
    }

    @Test
    void login_invalidPassword_shouldThrowInvalidPasswordException() {

        String login = "jojo";
        Password password= Password.builder().password(PASSWORD_COMPLIANT).build();

        when(userRepository.findUserByNameAndPassword(login, password.getPassword()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(InvalidPasswordException.class, () -> {
            userService.login(login,password);
        });
    }

    @Test
    void getUserByLogin_userExists_returnUserDto(){
        String login = "jojo";

        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);

        when(userRepository.findUserByName(loginCaptor.capture()))
                .thenReturn(Optional.of(UserTestUtil.getValidUserEntity()));

        when(userMapper.mapUserToUserDto(any(User.class))).thenReturn(UserTestUtil.getValidUserDTO());

        assertDoesNotThrow(() -> userService.getUserByLogin(login));

        assertEquals(login,loginCaptor.getValue());
    }

    @Test
    void getUserByLogin_userDoesntExists_throwUserNotFoundException(){
        String login = "jojo";

        ArgumentCaptor<String> loginCaptor = ArgumentCaptor.forClass(String.class);

        when(userRepository.findUserByName(loginCaptor.capture()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByLogin(login);
        });

        assertEquals(login,loginCaptor.getValue());

    }
}