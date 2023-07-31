package com.example.entity;

import com.example.Enum.ProfileRole;
import com.example.Enum.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity{


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ProfileRole role;

    @Column(name = "prt_id")
    private Integer prtId;

    @Column(name = "photo_id")
    private Integer photo_id;


}
