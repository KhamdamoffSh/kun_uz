package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO{
    private Integer id;
    private LocalDateTime created_date;
    private LocalDateTime update_date;
    private Integer profile_id;
    @NotBlank(message = "content is required")
    private String content;
    private String article_id;
    private Integer reply_id;
}
