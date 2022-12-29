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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ComplianceService complianceService;

    public UserDTO getUserByLogin(String login) throws UserNotFoundException {
       return userRepository.findUserByName(login)
               .map((userMapper::mapUserToUserDto))
               .orElseThrow(()-> new  UserNotFoundException(String.format("user %s login not found",login)));
    }

    public void registerUser(UserDTO userDTO) throws UserAlreadyExistsException, UserPasswordNotCompliantException {

        // check if user already exist
        Optional<User> userOptional = userRepository.findUserByName(userDTO.getName());

        if(userOptional.isPresent()){
            throw new UserAlreadyExistsException(String.format("user %s already exists",userDTO.getName()));
        }

        Compliance compliance = complianceService.getPasswordCompliance(userDTO.getPassword());

        if(!compliance.isValid()){
            throw new UserPasswordNotCompliantException("user password not compliant",compliance);
        }

        User userToCreate = userMapper.mapUserDtoToUser(userDTO);

        userRepository.save(userToCreate);
    }

    public void login(String login, Password password) throws InvalidPasswordException {
        userRepository.findUserByNameAndPassword(login, password.getPassword())
                .orElseThrow(() -> new InvalidPasswordException("invalid password"));
    }

}
