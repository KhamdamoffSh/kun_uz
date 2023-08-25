package com.example.dto;

import com.example.entity.ArticleEntity;
import com.example.entity.ProfileEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleSavedDTO {
    private Integer id;
    private String articleId;
    private Integer profileId;
}
