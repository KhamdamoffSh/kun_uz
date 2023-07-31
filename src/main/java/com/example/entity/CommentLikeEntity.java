package com.example.entity;

import com.example.Enum.CommentLikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "profile_id")
    private Integer profile_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profileId;

    @Column(name = "comment_id")
    private Integer comment_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id",insertable = false,updatable = false)
    private CommentEntity commentId;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "status")
    private CommentLikeStatus status;
}
