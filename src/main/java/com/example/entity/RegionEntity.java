package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "region")
public class RegionEntity extends BaseEntity{

    @Column(name = "order_number")
    private Integer order_number;

    @Column(name = "name_uz")
    private String name_uz;

    @Column(name = "name_ru")
    private String name_ru;

    @Column(name = "name_en")
    private String name_en;

}
