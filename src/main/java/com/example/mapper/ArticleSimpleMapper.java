package com.example.mapper;

import java.time.LocalDateTime;

public interface ArticleSimpleMapper {
    Integer getId();

    String getTitle();

    String getDescription();

    LocalDateTime getPublished_date();
}
