package com.example.products.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegisterDTO {

    @NotBlank(message = "Username must be provided!")
    @Size(min = 3, message = "Username must be at least 3 characters!")
    private String username;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Password must be provided!")
    @Size(min = 3, message = "Password cannot be less than 3 characters!")
    private String password;

    private String confirmPassword;

    @Email
    @NotBlank(message = "Email is required!")
    private String email;
}