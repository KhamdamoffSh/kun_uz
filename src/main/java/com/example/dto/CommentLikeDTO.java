package com.example.dto;

import com.example.Enum.CommentLikeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentLikeDTO {
    private Integer id;
    private Integer profile_id;
    private Integer comment_id;
    private LocalDateTime created_date;
    private CommentLikeStatus status;
}
