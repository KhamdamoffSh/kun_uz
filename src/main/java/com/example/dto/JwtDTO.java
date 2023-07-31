package com.example.dto;

import com.example.Enum.ProfileRole;
import com.example.entity.ArticleEntity;
import com.example.entity.ArticleLikeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private ProfileRole role;
    private ArticleEntity article;

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JwtDTO(Integer id, ProfileRole role, ArticleEntity article) {
        this.id = id;
        this.role = role;
        this.article = article;
    }

    public JwtDTO() {
    }
}
