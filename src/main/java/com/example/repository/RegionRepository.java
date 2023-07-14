package com.example.repository;

import com.example.entity.RegionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {

    @Transactional
    @Modifying
    @Query("update RegionEntity as r set r.order_number =:order_number,r.name_uz =:name_uz, r.name_ru =:name_ru, r.name_en =:name_en where r.id =:id")
    int updateById(@Param("id") Integer id, @Param("order_number") Integer order_number, @Param("name_uz") String name_uz, @Param("name_ru") String name_ru, @Param("name_en") String name_en);

    @Query("select r from RegionEntity as r where r.id =:id")
    Optional<RegionEntity> getById(Integer id);


    // TODO
    @Query("select r.name_uz from RegionEntity as r")
    Optional<RegionEntity> getByName_uz();




}
