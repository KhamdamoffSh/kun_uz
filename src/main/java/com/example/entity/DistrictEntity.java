package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessorOrder;

@Setter
@Getter
@Entity
@Table(name = "district")
public class DistrictEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Column(name = "nameUz")
    private String nameUz;

    @Column(name = "nameEn")
    private String nameEn;

    @Column(name = "nameRu")
    private String nameRu;
}
