package com.example.intractivtest.mapper;

import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapUserDtoToUser(UserDTO userDTO);
    UserDTO mapUserToUserDto(User user);
}
