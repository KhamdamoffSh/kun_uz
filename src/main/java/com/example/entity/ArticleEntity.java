package com.example.entity;

import com.example.Enum.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")

public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "shared_count")
    private Integer shared_count;

    @Column(name = "image_id")
    private Integer image_id;

    @Column(name = "region_id")
    private Integer region_id;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status;

    @Column(name = "moderator_id")
    private Integer moderator_id;

    @Column(name = "publisher_id")
    private Integer publisher_id;

    @Column(name = "created_date")
    LocalDateTime created_date;

    @Column(name = "publish_date")
    LocalDateTime publish_date;

    @Column(name = "visible")
    Boolean visible = true;

    @Column(name = "view_count")
    private Integer view_count;


}


