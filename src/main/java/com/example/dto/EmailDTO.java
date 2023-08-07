package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EmailDTO {
    private Integer id;
    @NotBlank(message = "message is required")
    private String message;
    @Email(message = "email is required")
    private String email;
    private LocalDateTime created_date;
}
