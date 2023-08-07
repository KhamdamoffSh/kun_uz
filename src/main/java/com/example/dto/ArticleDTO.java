package com.example.dto;

import com.example.Enum.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private String id;
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @NotBlank(message = "content is required")
    private String content;
    private Integer shared_count;
    private String image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer publisher_id;
    private Integer moderator_id;
    private ArticleStatus status;
    private List<Integer> articleType;
    private LocalDateTime created_date;
    private LocalDateTime published_date;
    private Integer view_count;



}
