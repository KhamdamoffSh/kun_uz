package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "articleType")
public class ArticleTypeEntity extends BaseEntity{

    @Column(name = "order_number")
    private Integer order_number;

    @Column(name = "name_uz")
    private String name_uz;

    @Column(name = "name_ru")
    private String name_ru;

    @Column(name = "name_eng")
    private String name_eng;

    @Column(name = "prtId")
    private Integer prtId;


}
