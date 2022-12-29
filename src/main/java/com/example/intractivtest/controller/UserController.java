package com.example.intractivtest.controller;

import com.example.intractivtest.dto.*;
import com.example.intractivtest.execption.InvalidPasswordException;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserNotFoundException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user")
@Tag(name = "CRUD", description = "Create Update Verify for user object")
public interface UserController {

    @Operation(summary = "Create a new user")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "user created"),
            @ApiResponse(responseCode = "400", description = "password not compliant", content = @Content(schema = @Schema(implementation = Compliance.class))),
            @ApiResponse(responseCode = "409", description = "user already exists", content = @Content(schema = @Schema(implementation = ApiError.class)))
        }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<SimpleMessage> createUser(@Parameter(required = true,
            description = "login to retrieve",
            schema = @Schema(implementation = UserDTO.class)) @RequestBody UserDTO userDTO) throws UserAlreadyExistsException, UserPasswordNotCompliantException;


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "user found", content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "404", description = "user not found", content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @GetMapping(path = "/{login}", produces = {"application/json","application/text"})
    ResponseEntity<UserDTO> getUserByLogin(@PathVariable("login") String login) throws UserNotFoundException;

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "password is ok", content = @Content(schema = @Schema(implementation = SimpleMessage.class))),
                    @ApiResponse(responseCode = "403", description = "invalid password", content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    @PostMapping(path = "/{login}/verify", consumes = "application/json", produces = "application/json")
    ResponseEntity<SimpleMessage> login(@PathVariable("login") String login, @RequestBody Password password) throws InvalidPasswordException;


}
