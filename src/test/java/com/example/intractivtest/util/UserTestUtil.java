package com.example.intractivtest.util;

import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.entity.User;

public class UserTestUtil {

    public static UserDTO getValidUserDTO(){
        return UserDTO.builder()
                .name("validNam")
                .phone("0123456789")
                .password("ValidPassword57!")
                .build();
    }

    public static User getValidUserEntity(){
        return User.builder()
                .name("validNam")
                .phone("0123456789")
                .password("ValidPassword57!")
                .build();
    }

}
