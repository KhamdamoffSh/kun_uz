package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFilterDTO {
    private Integer id;
    private LocalDate created_date_from;
    private LocalDate created_date_to;
    private Integer profile_id;
    private String article_id;

}
