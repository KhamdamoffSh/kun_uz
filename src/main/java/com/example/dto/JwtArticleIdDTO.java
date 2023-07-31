package com.example.dto;

import com.example.entity.ArticleEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtArticleIdDTO {
    private Integer id;
    private ArticleEntity article;

}
