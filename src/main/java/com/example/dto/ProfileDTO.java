package com.example.dto;

import com.example.Enum.ProfileRole;
import com.example.Enum.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private LocalDateTime created_date;
    private Integer photo_id;
}
