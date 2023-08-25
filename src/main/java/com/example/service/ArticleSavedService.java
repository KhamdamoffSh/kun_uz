package com.example.service;

import com.example.dto.ArticleSavedDTO;
import com.example.entity.ArticleSavedEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ArticleSavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleSavedService {

    @Autowired
    private ArticleSavedRepository articleSavedRepository;

    public ArticleSavedDTO add(ArticleSavedDTO dto) {

        ArticleSavedEntity entity = new ArticleSavedEntity();
        entity.setId(dto.getId());
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
        articleSavedRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public Boolean deleteByArticleId(String articleId) {
        Optional<ArticleSavedEntity> optional = articleSavedRepository.getByArticleId(articleId);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Article not faund");
        }
        articleSavedRepository.delete(optional.get());
        return true;
    }

    public List<ArticleSavedDTO> getAllSavedArticle(){
        List<ArticleSavedDTO> list = new LinkedList<>();
        Iterable<ArticleSavedEntity> iterable = articleSavedRepository.getAllSavedArticle();
        iterable.forEach(e ->{
            list.add(toDTO(e));
        });
        return list;
    }

    public ArticleSavedDTO toDTO(ArticleSavedEntity entity){
        ArticleSavedDTO dto = new ArticleSavedDTO();
        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setProfileId(entity.getProfileId());

        return dto;
    }

}
