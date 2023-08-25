package com.example.entity;

import com.example.Enum.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity extends BaseStringEntity{

    @Column(name = "title",nullable = false,unique = true)
    private String title;

    @Column(name = "description",nullable = false,columnDefinition = "text")
    private String description;

    @Column(name = "content",nullable = false,columnDefinition = "text")
    private String content;

    @Column(name = "shared_count")
    private Integer shared_count = 0;

    @Column(name = "view_count")
    private Integer view_count = 0;

    @Column(name = "likeCount")
    private String likeCount;

    @Column(name = "disLikeCount")
    private String dislike;

    @Column(name = "image_id",nullable = false)
    private String image_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",insertable = false,updatable = false)
    private AttachEntity image;

    @Column(name = "region_id",nullable = false)
    private Integer region_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "category_id",nullable = false)
    private Integer category_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;

    @Column(name = "article_type_id")
    private Integer article_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_type",insertable = false,updatable = false)
    private ArticleTypeEntity articleType;

    @Column(name = "moderator_id",nullable = false)
    private Integer moderator_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id",insertable = false,updatable = false)
    private ProfileEntity moderator;

    @Column(name = "publisher_id",nullable = false)
    private Integer publisher_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id",insertable = false,updatable = false)
    private ProfileEntity publisher;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;

    @Column(name = "publish_date")
    LocalDateTime publish_date;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private Set<ArticleTypesEntity> articleTypeSet;
}


