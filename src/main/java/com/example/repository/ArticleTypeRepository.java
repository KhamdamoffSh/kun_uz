package com.example.repository;

import com.example.entity.ArticleTypeEntity;
import com.example.entity.ProfileEntity;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>,
        PagingAndSortingRepository<ArticleTypeEntity, Integer> {


    @Transactional
    @Modifying
    @Query("update ArticleTypeEntity as a set a.order_number =:order_number,a.name_uz =:name_uz,a.name_ru =:name_ru,a.name_eng =:name_eng where a.id =:id")
    int updateById(@Param("id") Integer id, @Param("order_number") Integer order_number, @Param("name_uz") String name_uz, @Param("name_ru") String name_ru, @Param("name_eng") String name_eng);


    @Query("select a from ArticleTypeEntity as a where a.id =:id")
    Optional<ArticleTypeEntity> getById(Integer id);

    List<ArticleTypeEntity> findAllByVisibleTrue();

}
