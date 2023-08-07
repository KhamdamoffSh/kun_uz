package com.example.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegistrationDTO{
    private Integer id;
//    @NotNull(message = "Name is required")
//    @Size(min = 3, message = "Name should be at least 3 characters")
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "surname is required")
    private String surname;
    @Email(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;

//    @NotEmpty(message = "numbers is required")
//    private List<Integer> numbers;
}
