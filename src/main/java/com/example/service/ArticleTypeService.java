package com.example.service;

import com.example.Enum.Language;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.RegionDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.exaption.AppBadRequestException;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO add(ArticleTypeDTO dto){

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.getId());
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_eng(dto.getName_eng());
        entity.setCreated_date(LocalDateTime.now());
        articleTypeRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());

        return dto;
    }

    public Boolean updateById(Integer id, ArticleTypeDTO dto){
        check(dto);

        int effectedRows = articleTypeRepository.updateById(id, dto.getOrder_number(), dto.getName_uz(), dto.getName_ru(), dto.getName_eng());
        return effectedRows == 1;
    }


    public Boolean deleteById(Integer id){
        Optional<ArticleTypeEntity> optional = articleTypeRepository.getById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("article not found");
        }
        optional.ifPresent(articleTypeRepository::delete);
        return true;
    }



    public List<ArticleTypeDTO> getAll(){
        Iterable<ArticleTypeEntity> iterable = articleTypeRepository.findAll();
        List<ArticleTypeDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
    }


    public List<ArticleTypeDTO> getByLanguage(Language lang){
        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        articleTypeRepository.findAllByVisibleTrue().forEach(entity -> {
            dtoList.add(getByLanguageLogica(entity,lang));
        });
        return dtoList;
    }

    public ArticleTypeDTO getByLanguageLogica(ArticleTypeEntity entity, Language language){
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        switch (language){
            case en -> dto.setName(entity.getName_eng());
            case uz -> dto.setName(entity.getName_uz());
            case ru -> dto.setName(entity.getName_ru());
            default -> dto.setName(entity.getName_uz());
        }
        return dto;
    }






    private void check (ArticleTypeDTO dto) throws RuntimeException {
        if (dto.getOrder_number() == null) {
            throw new AppBadRequestException("order number is null");
        }
        if (dto.getName_eng() == null || dto.getName_eng().isBlank()) {
            throw new AppBadRequestException("name english is null");
        }
        if (dto.getName_uz() == null || dto.getName_uz().isBlank()) {
            throw new AppBadRequestException("name uzbek is null");
        }
        if (dto.getName_ru() == null || dto.getName_ru().isBlank()) {
            throw new AppBadRequestException("name russia is null");
        }
    }


    public ArticleTypeDTO toDTO(ArticleTypeEntity entity){
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        dto.setName_uz(entity.getName_uz());
        dto.setName_eng(entity.getName_eng());
        dto.setName_ru(entity.getName_ru());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }
}
