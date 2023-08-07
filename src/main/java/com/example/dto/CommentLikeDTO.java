package com.example.dto;

import com.example.Enum.CommentLikeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentLikeDTO {
    private Integer id;
    private Integer profile_id;
    @NotNull(message = "comment_id is required")
    private Integer comment_id;
    private LocalDateTime created_date;
    private CommentLikeStatus status;
}
