package com.example.intractivtest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User")
public class UserDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    @NotBlank
    private String password;

}
