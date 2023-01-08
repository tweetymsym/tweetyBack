package com.tweety.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "Username should not be blank!")
    private String userName;
    @NotBlank(message = "First name should not be blank!")
    private String firstName;
    @NotBlank(message = "Last name should not be blank!")
    private String lastName;
    @NotBlank(message = "Email should not be blank!")
    private String email;
    @NotBlank(message = "Password should not be blank!")
    private String password;
}
