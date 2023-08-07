package com.example.service;

import com.example.Enum.ArticleStatus;
import com.example.dto.ArticleDTO;
import com.example.entity.ArticleEntity;
import com.example.entity.ArticleTypeEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import org.aspectj.util.LangUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ArticleTypesService articleTypesService;
    @Autowired
    private CategoryService categoryService;

    public ArticleDTO add(ArticleDTO dto) {

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setDescription(dto.getDescription());
        entity.setImage_id(dto.getImage_id());
        entity.setRegion_id(dto.getRegion_id());
        entity.setCategory_id(dto.getCategory_id());
        entity.setArticleType(articleRepository.getArticleType(toDTO((ArticleEntity) dto.getArticleType()).getArticleType()));
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        entity.setModerator_id(dto.getModerator_id());
        articleRepository.save(entity);
        articleTypesService.create(entity.getId(), dto.getArticleType());
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());

        return dto;
    }

    public Boolean updateById(Integer id, ArticleDTO dto) {
        int effect = articleRepository.updateById(id, dto.getTitle(), dto.getDescription(), dto.getContent(), dto.getRegion_id(), dto.getCategory_id());
        return effect == 1;
    }

    public Boolean deleteById(Integer id) {
        Optional<ArticleEntity> optional = articleRepository.getById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("article not found");
        }
        optional.ifPresent(articleRepository::delete);
        return true;
    }

    public Boolean changeStatusById(Integer id) {
        int effect = articleRepository.changeStatusbyId(id, ArticleStatus.PUBLISHED);
        return effect == 1;
    }

    public List<ArticleDTO> lastFiveArticle(Integer articleTypeId){
        Iterable<ArticleEntity> iterable = articleRepository.getLastFiveArticle(articleTypeId,5);
        List<ArticleDTO> list = new LinkedList<>();
        iterable.forEach(i ->{
            list.add(toDTO(i));
        });
        return list;
    }

    public List<ArticleDTO> lastThreeArticle(Integer articleTypeId){
        Iterable<ArticleEntity> iterable = articleRepository.getLastFiveArticle(articleTypeId,3);
        List<ArticleDTO> list = new LinkedList<>();
        iterable.forEach(i ->{
            list.add(toDTO(i));
        });
        return list;
    }


    public List<ArticleDTO> notGivenId(String id){
        Iterable<ArticleEntity> iterable = articleRepository.notGivenId(id);
        List<ArticleDTO> list = new LinkedList<>();
        iterable.forEach(e ->{
            list.add(toDTO(e));
        });
        return list;
    }



    public List<ArticleDTO> getByIdAndLang(Integer articleId, LangUtil lang){
        Optional<ArticleEntity> optional = articleRepository.getById(articleId);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Aticle not faund");
        }
        return Collections.singletonList(toDetailDTO(optional.get(), lang));
    }


    public List<ArticleDTO> getByLast4Article(String articleId,Integer articleTypeId){
        List<ArticleDTO> list = new LinkedList<>();
        Iterable<ArticleEntity> iterable = articleRepository.getLast4ArticleTypeIdAndExcept(articleId,articleTypeId);

        for (ArticleEntity a : iterable){
            list.add(toDTO(a));
        }
        return list;

    }



    public ArticleDTO toDetailDTO(ArticleEntity entity, LangUtil lang) {
        ArticleDTO dto = toDTO(entity);

        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setModerator_id(entity.getModerator_id());
        dto.setPublished_date(entity.getPublish_date());
        dto.setTitle(entity.getContent());
        dto.setCategory_id(entity.getCategory_id());
        dto.setRegion_id(entity.getRegion_id());
        dto.setStatus(entity.getStatus());
        dto.setShared_count(entity.getShared_count());
        dto.setCreated_date(entity.getCreated_date());
        dto.setDescription(entity.getDescription());
        return dto;
    }



    public ArticleDTO toDTO(ArticleEntity entity) {
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
