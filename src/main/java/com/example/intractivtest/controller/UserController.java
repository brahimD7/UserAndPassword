package com.example.intractivtest.controller;

import com.example.intractivtest.dto.Password;
import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.execption.InvalidPasswordException;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserNotFoundException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
@Tag(name = "CRUD", description = "Create Update Verify for user object")
public interface UserController {

    @Operation(summary = "Create a new user")
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<String> createUser(@Parameter(required = true,
            description = "login to retrieve",
            schema = @Schema(implementation = UserDTO.class)) @RequestBody UserDTO userDTO) throws UserAlreadyExistsException, UserPasswordNotCompliantException;

    @GetMapping(path = "/{login}", produces = "application/json")
    ResponseEntity<UserDTO> getUserByLogin(@PathVariable("login") String login) throws UserNotFoundException;

    @PostMapping(path = "/{login}/verify", consumes = "application/json", produces = "application/json")
    ResponseEntity<String> login(@PathVariable("login") String login, @RequestBody Password password) throws InvalidPasswordException;


}
