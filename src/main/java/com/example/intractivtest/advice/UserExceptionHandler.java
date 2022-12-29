package com.example.intractivtest.advice;

import com.example.intractivtest.dto.ApiError;
import com.example.intractivtest.execption.InvalidPasswordException;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserNotFoundException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.builder().statusCode(HttpStatus.NOT_FOUND.value()).message(userNotFoundException.getMessage()).build());
    }

    @ExceptionHandler(value = UserPasswordNotCompliantException.class)
    public ResponseEntity<Object> handleUserPasswordNotCompliant(UserPasswordNotCompliantException userPasswordNotCompliantException) {
        return ResponseEntity.badRequest().body(userPasswordNotCompliantException.getCompliance());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.builder().statusCode(HttpStatus.CONFLICT.value()).message(userAlreadyExistsException.getMessage()).build());
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<ApiError> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiError.builder().statusCode(HttpStatus.FORBIDDEN.value()).message(invalidPasswordException.getMessage()).build());
    }

}
