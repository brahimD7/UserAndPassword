package com.example.intractivtest.controller;

import com.example.intractivtest.dto.Compliance;
import com.example.intractivtest.dto.UserDTO;
import com.example.intractivtest.execption.UserAlreadyExistsException;
import com.example.intractivtest.execption.UserPasswordNotCompliantException;
import com.example.intractivtest.service.UserService;
import com.example.intractivtest.util.UserTestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_KEY = "Content-Type";

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void registerUser_ValidUser_return201() throws Exception {

        UserDTO userDTO = UserTestUtil.getValidUserDTO();

        doNothing().when(userService).registerUser(any(UserDTO.class));

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(header().string(CONTENT_TYPE_KEY, APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void registerUser_UserAlreadyExists_return409() throws Exception {

        UserDTO userDTO = UserTestUtil.getValidUserDTO();

        doThrow(new UserAlreadyExistsException("User already exists")).when(userService).registerUser(any(UserDTO.class));

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    public void registerUser_UserPasswordNotCompliant_return400() throws Exception {

        UserDTO userDTO = UserTestUtil.getValidUserDTO();

        doThrow(new UserPasswordNotCompliantException("password not compliant",new Compliance(false,"")))
                .when(userService).registerUser(any(UserDTO.class));

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.valid").value(false));

    }
}
