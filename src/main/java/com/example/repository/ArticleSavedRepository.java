package com.example.repository;

import com.example.entity.ArticleSavedEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleSavedRepository extends CrudRepository<ArticleSavedEntity, Integer> {

    @Query("from ArticleSavedEntity as a where a.articleId =:articleId")
    Optional<ArticleSavedEntity> getByArticleId( String id);


    @Query("from ArticleSavedEntity")
    List<ArticleSavedEntity> getAllSavedArticle();
}
