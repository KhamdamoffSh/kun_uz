package com.example.repository;

import com.example.dto.CommentFilterDTO;
import com.example.dto.FilterResultDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.entity.CommentEntity;
import com.example.entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CastomProfileRepository {

    @Autowired
    private EntityManager entityManager;

    public FilterResultDTO<ProfileEntity> filter(ProfileFilterDTO filterDTO, int page, int size) {

        StringBuilder stringBuilder = new StringBuilder();

        Map<String, Object> params = new HashMap<>();
        if (filterDTO.getName() != null) {
            stringBuilder.append(" and lower(s.name) =:name");
            params.put("name", filterDTO.getName().toLowerCase());
        }
        if (filterDTO.getSurname() != null) {
            stringBuilder.append(" and s.surname =:surname");
            params.put("surname", filterDTO.getSurname());
        }
        if (filterDTO.getPhone() != null) {
            stringBuilder.append(" and s.phone =:phone");
            params.put("phone", filterDTO.getPhone());
        }
        if (filterDTO.getRole() != null) {
            stringBuilder.append(" and s.role =:role");
            params.put("role", filterDTO.getRole());
        }
        if (filterDTO.getCreated_date_from() != null && filterDTO.getCreated_date_to() != null) {
            stringBuilder.append(" and s.created_date between :created_date_from and :created_date_to ");
            params.put("created_date_from", LocalDateTime.of(filterDTO.getCreated_date_from(), LocalTime.MIN));
            params.put("created_date_to", LocalDateTime.of(filterDTO.getCreated_date_to(), LocalTime.MAX));
        } else if (filterDTO.getCreated_date_from() != null) {
            stringBuilder.append(" and s.created_date >= :created_date_from");
            params.put("created_date_from", LocalDateTime.of(filterDTO.getCreated_date_from(), LocalTime.MIN));
        }else if (filterDTO.getCreated_date_to() != null){
            stringBuilder.append(" and s.created_date <= :created_date_to");
            params.put("created_date_to",LocalDateTime.of(filterDTO.getCreated_date_to(),LocalTime.MAX));
        }

        StringBuilder selectBuilder = new StringBuilder("select s from ProfileEntity as s where s.visible = true ");
        selectBuilder.append(stringBuilder);

        StringBuilder countBuilder = new StringBuilder("select count(s) from ProfileEntity as s where s.visible = true ");
        countBuilder.append(stringBuilder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult(size * page);

        Query countQuery = entityManager.createQuery(countBuilder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }

        List<ProfileEntity> select = selectQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();

        return new FilterResultDTO<>(select,count);
    }


    public FilterResultDTO<CommentEntity> commentFilter(CommentFilterDTO filterDTO, int page, int size) {

        StringBuilder stringBuilder = new StringBuilder();

        Map<String, Object> params = new HashMap<>();
        if (filterDTO.getProfile_id() != null) {
            stringBuilder.append(" and s.profile_id =:profile_id");
            params.put("profile_id", filterDTO.getProfile_id());
        }
        if (filterDTO.getArticle_id() != null) {
            stringBuilder.append(" and s.article_id =:article_id");
            params.put("article_id", filterDTO.getArticle_id());
        }
        if (filterDTO.getId() != null) {
            stringBuilder.append(" and s.id =:id");
            params.put("id", filterDTO.getId());
        }
        if (filterDTO.getCreated_date_from() != null && filterDTO.getCreated_date_to() != null) {
            stringBuilder.append(" and s.created_date between :created_date_from and :created_date_to ");
            params.put("created_date_from", LocalDateTime.of(LocalDate.from(filterDTO.getCreated_date_from()), LocalTime.MIN));
            params.put("created_date_to", LocalDateTime.of(LocalDate.from(filterDTO.getCreated_date_to()), LocalTime.MAX));
        } else if (filterDTO.getCreated_date_from() != null) {
            stringBuilder.append(" and s.created_date >= :created_date_from");
            params.put("created_date_from", LocalDateTime.of(LocalDate.from(filterDTO.getCreated_date_from()), LocalTime.MIN));
        }else if (filterDTO.getCreated_date_to() != null){
            stringBuilder.append(" and s.created_date <= :created_date_to");
            params.put("created_date_to",LocalDateTime.of(filterDTO.getCreated_date_to(), LocalTime.MAX));
        }

        StringBuilder selectBuilder = new StringBuilder("select s from CommentEntity as s where 1=1");
        selectBuilder.append(stringBuilder);

        StringBuilder countBuilder = new StringBuilder("select count(s) from CommentEntity as s where ");
        countBuilder.append(stringBuilder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult(size * page);

        Query countQuery = entityManager.createQuery(countBuilder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }

        List<CommentEntity> select = selectQuery.getResultList();
        Long count = (Long) countQuery.getSingleResult();

        return new FilterResultDTO<>(select,count);
    }
}
