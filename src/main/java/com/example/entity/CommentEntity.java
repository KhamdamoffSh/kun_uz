package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity{

    @Column(name = "update_date")
    private LocalDateTime update_date;

    @Column(name = "profile_id")
    private Integer profile_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profileId;


    @Column(name = "content")
    private String content;

    @Column(name = "article_id",nullable = false)
    private String article_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity articleId;

    @Column(name = "reply_id")
    private Integer reply_id;

    @Column(name = "visible")
    private Boolean visible = true;

}
