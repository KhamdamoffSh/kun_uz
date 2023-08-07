package com.example.dto;

import com.example.Enum.ProfileRole;
import com.example.Enum.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProfileDTO {
    private Integer id;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "surname is required")
    private String surname;
    @Email(message = "email is required")
    private String email;
    @NotBlank(message = "phone is required")
    private String phone;
    @NotBlank(message = "password is required")
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private LocalDateTime created_date;
    private Integer photo_id;
    private String jwt;
}
