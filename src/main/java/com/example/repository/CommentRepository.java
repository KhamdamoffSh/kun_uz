package com.example.repository;

import com.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CommentRepository<C, I extends Number> extends CrudRepository<CommentEntity, Integer>,
        PagingAndSortingRepository<CommentEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update CommentEntity as c set c.content =:content where c.id =:id")
    int update(@Param("content") String content, @Param("id") Integer id);

    @Query("select c from CommentEntity as c where c.id =:id")
    Optional<CommentEntity> getById(Integer id);

}
