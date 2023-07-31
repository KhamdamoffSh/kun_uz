package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @Column(name = "order_number")
    private Integer order_number;

    @Column(name = "name_uz",nullable = false,updatable = false)
    private String name_uz;

    @Column(name = "name_ru",nullable = false,updatable = false)
    private String name_ru;

    @Column(name = "name_en",nullable = false,updatable = false)
    private String name_en;

    @Column(name = "prt_id")
    private Integer patId;


}
