package com.example.dto;

import com.example.Enum.ProfileRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String phone;
    private ProfileRole role;
    private LocalDate created_date_from;
    private LocalDate created_date_to;

}
