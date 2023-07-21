package com.example.service;

import com.example.Enum.ArticleStatus;
import com.example.dto.ArticleDTO;
import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.ArticleTypeEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    int count = 0;

    public ArticleDTO add(ArticleDTO dto){

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setShared_count(count++);
        entity.setImage_id(dto.getImage_id());
        entity.setRegion_id(dto.getRegion_id());
        entity.setCategory_id(dto.getCategory_id());
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        entity.setModerator_id(dto.getModerator_id());
        entity.setPublisher_id(dto.getPublisher_id());
        entity.setCreated_date(LocalDateTime.now());
        articleRepository.save(entity);
        dto.setId(entity.getId());

        return dto;
    }

    public Boolean updateById(Integer id, ArticleDTO dto){
        int effect = articleRepository.updateById(id,dto.getTitle(),dto.getDescription(),dto.getContent(),dto.getRegion_id(),dto.getCategory_id());
        return effect == 1;
    }

    public Boolean deleteById(Integer id){
        Optional<ArticleEntity> optional = articleRepository.getById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("article not found");
        }
        optional.ifPresent(articleRepository::delete);
        return true;
    }

    public Boolean changeStatusById(Integer id){
        int effect = articleRepository.changeStatusbyId(id,ArticleStatus.PUBLISHED);
        return effect == 1;
    }

    public List<ArticleDTO> lastFiveArticle(){
        List<ArticleDTO> list = new LinkedList<>();

        articleRepository.getLastFiveArticle().forEach(articleEntity -> {
            list.add(toDTO(articleEntity));
        });
        return list;
    }


    public ArticleDTO toDTO(ArticleEntity entity){
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setModerator_id(entity.getModerator_id());
        dto.setPublisher_id(entity.getPublisher_id());
        dto.setStatus(entity.getStatus());
        dto.setImage_id(entity.getImage_id());
        dto.setCategory_id(entity.getCategory_id());
        dto.setRegion_id(entity.getRegion_id());
        dto.setView_count(entity.getView_count());
        dto.setShared_count(entity.getShared_count());
        dto.setPublished_date(entity.getPublish_date());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }
}
