package com.example.service;

import com.example.Enum.ArticleLikeStatus;
import com.example.Enum.CommentLikeStatus;
import com.example.dto.ArticleLikeDTO;
import com.example.dto.CommentLikeDTO;
import com.example.entity.ArticleLikeEntity;
import com.example.entity.CommentLikeEntity;
import com.example.repository.ArticleLikeRepository;
import com.example.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public CommentLikeDTO like(CommentLikeDTO dto, Integer profile_id){
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setProfile_id(profile_id);
        entity.setComment_id(dto.getComment_id());
        entity.setStatus(CommentLikeStatus.LIKE);
        entity.setCreated_date(dto.getCreated_date());
        commentLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }



    public CommentLikeDTO dislike(CommentLikeDTO dto, Integer profile_id){
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setProfile_id(profile_id);
        entity.setComment_id(dto.getComment_id());
        entity.setStatus(CommentLikeStatus.DISLIKE);
        entity.setCreated_date(dto.getCreated_date());
        commentLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }

    public CommentLikeDTO remove(CommentLikeDTO dto, Integer profile_id){
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setProfile_id(profile_id);
        entity.setComment_id(dto.getComment_id());
        entity.setStatus(null);
        entity.setCreated_date(dto.getCreated_date());
        commentLikeRepository.save(entity);
        dto.setCreated_date(entity.getCreated_date());
        dto.setId(entity.getId());

        return dto;
    }
}
