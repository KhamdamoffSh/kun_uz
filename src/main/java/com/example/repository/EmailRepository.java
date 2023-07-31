package com.example.repository;

import com.example.entity.EmailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends CrudRepository<EmailEntity, Integer>,
        PagingAndSortingRepository<EmailEntity, Integer> {

    void delete(EmailEntity emailEntity);

    @Query("select e from EmailEntity as e where e.email =:email")
    Optional<EmailEntity> getByEmail(@Param("email") String email);

    /*Iterable<EmailEntity> findAllByCreated_date();*/



}
