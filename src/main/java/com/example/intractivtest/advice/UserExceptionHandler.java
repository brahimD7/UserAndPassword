package com.example.intractivtest.advice;

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
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = UserPasswordNotCompliantException.class)
    public ResponseEntity<Object> handleUserPasswordNotCompliant(UserPasswordNotCompliantException userPasswordNotCompliantException) {
        return ResponseEntity.badRequest().body(userPasswordNotCompliantException.getCompliance());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
         return ResponseEntity.status(HttpStatus.CONFLICT).body(userAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(value = InvalidPasswordException.class)
    public ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException) {
        return ResponseEntity.badRequest().body(invalidPasswordException.getMessage());
    }

}
