package com.example.repository;

import com.example.entity.ProfileEntity;
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
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>,
        PagingAndSortingRepository<ProfileEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update ProfileEntity as s set s.name =:name, s.surname =:surname, s.email =:email, s.phone =:phone, s.password =:password where s.id =:id")
    int updatebyId(@Param("id") Integer id, @Param("name") String name, @Param("surname") String surname, @Param("email") String email, @Param("phone") String phone, @Param("password") String password);

    Page<ProfileEntity> findAllByName(Pageable pageable,String name);

    Optional<ProfileEntity> findByPhone(String phone);


    @Query("select s from ProfileEntity as s where s.id =:id")
    Optional<ProfileEntity> getById(Integer id);


    Optional<ProfileEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update ProfileEntity  set name =?2, surname =?3 where id =?1")
    int updateDetail(Integer id, String name, String surname);
}
