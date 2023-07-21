package com.example.dto;

import com.example.Enum.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter

public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private String content;
    private Integer shared_count;
    private Integer image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer publisher_id;
    private Integer moderator_id;
    private ArticleStatus status;
    private LocalDateTime created_date;
    private LocalDateTime published_date;
    private boolean visible;
    private Integer view_count;



}
