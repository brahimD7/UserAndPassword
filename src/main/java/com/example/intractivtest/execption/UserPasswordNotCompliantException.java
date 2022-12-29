package com.example.intractivtest.execption;

import com.example.intractivtest.dto.Compliance;
import lombok.Getter;

@Getter
public class UserPasswordNotCompliantException extends Exception{

    private final Compliance compliance;

    public UserPasswordNotCompliantException(String message, Compliance compliance) {
        super(message);
        this.compliance = compliance;
    }
}
