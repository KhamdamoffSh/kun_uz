package com.example.service;

import com.example.Enum.ArticleLikeStatus;
import com.example.dto.ArticleLikeDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.entity.ProfileEntity;
import com.example.repository.ArticleLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeService {

    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public ArticleLikeDTO like(ArticleLikeDTO dto, Integer profile_id){
        ArticleLikeEntity entity = new ArticleLikeEntity();
       entity.setArticle_id(dto.getArticle_id());
        entity.setProfile_id(profile_id);
        entity.setStatus(ArticleLikeStatus.LIKE);
        entity.setCreated_date(dto.getCreated_date());
        articleLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }



    public ArticleLikeDTO dislike(ArticleLikeDTO dto, Integer profile_id){
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setArticle_id(dto.getArticle_id());
        entity.setProfile_id(profile_id);
        entity.setStatus(ArticleLikeStatus.DISLIKE);
        entity.setCreated_date(dto.getCreated_date());
        articleLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }

    public ArticleLikeDTO remove(ArticleLikeDTO dto, Integer profile_id){
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setArticle_id(dto.getArticle_id());
        entity.setProfile_id(profile_id);
        entity.setStatus(null);
        entity.setCreated_date(dto.getCreated_date());
        articleLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }
}
