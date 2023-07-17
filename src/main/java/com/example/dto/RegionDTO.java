package com.example.dto;

import com.example.Enum.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {
    private Integer id;
    private Integer order_number;
    private String name;
    private String name_uz;
    private String name_ru;
    private String name_en;
    private LocalDateTime created_date;

}
