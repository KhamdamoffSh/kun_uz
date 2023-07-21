package com.example.repository;

import com.example.Enum.ArticleStatus;
import com.example.dto.ArticleDTO;
import com.example.entity.ArticleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update ArticleEntity as a set a.title =:title, a.description =:description, a.content =:content, a.region_id =:region_id, a.category_id =:category_id where a.id =:id")
    int updateById(@Param("id") Integer id, @Param("title") String title, @Param("description") String description, @Param("content") String content, @Param("region_id") Integer region_id, @Param("category_id") Integer category_id);


    @Query("select a from ArticleEntity as a where a.id =:id")
    Optional<ArticleEntity> getById(Integer id);

    @Transactional
    @Modifying
    @Query("update ArticleEntity as a set a.status =:status where a.id=:id")
    int changeStatusbyId(@Param("id") Integer id, @Param("status") ArticleStatus status);


    @Query("from ArticleEntity order by created_date desc limit 5")
    List<ArticleEntity>getLastFiveArticle();















}

