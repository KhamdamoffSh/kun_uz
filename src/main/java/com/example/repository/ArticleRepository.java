package com.example.repository;

import com.example.Enum.ArticleStatus;
import com.example.entity.ArticleEntity;
import com.example.entity.ArticleTypeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {

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


    @Query("from ArticleEntity as a " +
            "inner join a.articleTypeSet as at" +
            " where at.articleTypeId =:articleTypeId" +
            " and a.status =PUBLISHED" +
            " and a.visible = true order by a.publish_date desc limit :limit")
    List<ArticleEntity>getLastFiveArticle(@Param("articleTypeId")Integer articleTypeId,
                                          @Param("limit")int limit);


    @Query("from ArticleEntity as a " +
            "inner join a.articleTypeSet as at" +
            " where at.articleTypeId =:articleTypeId" +
            " and a.status =PUBLISHED" +
            " and a.visible = true order by a.publish_date desc limit :limit")
    List<ArticleEntity>getLastThreeArticle(@Param("articleTypeId")Integer articleTypeId,
                                          @Param("limit")int limit);


    @Query("from ArticleEntity  as a " +
            "inner join a.articleTypeSet as at" +
            " where at.articleTypeId =:articleTypeId" +
            " and a.id <>:articleId" +
            " and a.status =:status and a.visible = true order by a.publish_date desc limit 4")
    List<ArticleEntity> getLast4ArticleTypeIdAndExcept(@Param("articleId")String articleId,
                                                       @Param("articleTypeId") Integer articleTypeId,
                                                       @Param("status") ArticleStatus status);



    @Query("select a.name_uz from ArticleTypeEntity as a where a.id=:id")
    ArticleTypeEntity getArticleType(List<Integer> id);












}

