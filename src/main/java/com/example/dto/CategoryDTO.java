package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Integer id;
    @NotBlank(message = "order_number is required")
    private Integer order_number;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "name_uz is required")
    private String name_uz;
    @NotBlank(message = "name_ru is required")
    private String name_ru;
    @NotBlank(message = "name_eng is required")
    private String name_en;
    private LocalDateTime created_date;

}
