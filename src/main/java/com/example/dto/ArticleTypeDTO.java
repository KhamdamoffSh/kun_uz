package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.lang.ref.SoftReference;
import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    private Integer id;
    private Integer order_number;
    private String name;
    private String name_uz;
    private String name_eng;
    private String name_ru;
    private LocalDateTime created_date;
}
