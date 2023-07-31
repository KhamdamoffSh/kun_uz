package com.example.dto;

import com.example.Enum.ArticleLikeStatus;
import com.example.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleLikeDTO {
    private Integer id;
    private Integer profile_id;
    private String article_id;
    private LocalDateTime created_date = LocalDateTime.now();
    private ArticleLikeStatus status;
}
