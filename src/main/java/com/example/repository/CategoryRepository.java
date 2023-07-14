package com.example.repository;

import com.example.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update CategoryEntity as c set c.order_number =:order_number,c.name_uz =:name_uz,c.name_ru =:name_ru,c.name_en =:name_en where c.id =:id")
    int updateById(@Param("id") Integer id,@Param("order_number") Integer order_number, @Param("name_uz") String name_uz, @Param("name_ru") String name_ru, @Param("name_en") String name_en);


    @Query("select c from CategoryEntity as c where c.id =:id")
    Optional<CategoryEntity> getById(Integer id);

}
