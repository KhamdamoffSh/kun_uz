package com.example.entity;

import com.example.Enum.ArticleLikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "articleLike")
public class ArticleLikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "profile_id")
    private Integer profile_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profileId;

    @Column(name = "article_id")
    private String article_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity articleId;

    @Column(name = "created_date")
    private LocalDateTime created_date = LocalDateTime.now();

    @Column(name = "status")
   @Enumerated(EnumType.STRING)
    private ArticleLikeStatus status;


}
