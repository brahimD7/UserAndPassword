package com.example.intractivtest.controller.impl;

import com.example.intractivtest.controller.UserController;
import com.example.intractivtest.dto.Password;
import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.execption.InvalidPasswordException;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserNotFoundException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import com.example.intractivtest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/user")
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<String> createUser(UserDTO userDTO) throws UserAlreadyExistsException, UserPasswordNotCompliantException {
        userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("user created");
    }

    @Override
    public ResponseEntity<UserDTO> getUserByLogin(String login) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @Override
    public ResponseEntity<String> login(String login, Password password) throws InvalidPasswordException {
        userService.login(login,password);
        return ResponseEntity.ok("password is ok");
    }
}
