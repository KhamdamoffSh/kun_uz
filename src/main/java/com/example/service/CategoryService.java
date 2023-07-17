package com.example.service;

import com.example.Enum.Language;
import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO add(CategoryDTO dto) {

        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setCreated_date(LocalDateTime.now());
        categoryRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());

        return dto;
    }

    public Boolean updateById(Integer id, CategoryDTO dto){
        int effect = categoryRepository.updateById(id, dto.getOrder_number(), dto.getName_uz(), dto.getName_ru(), dto.getName_en());
        return effect == 1;
    }

    public Boolean deleteById(Integer id){
        Optional<CategoryEntity> optional = categoryRepository.getById(id);
        optional.ifPresent(categoryRepository::delete);
        return true;
    }

    public List<CategoryDTO> getAll(){
        Iterable<CategoryEntity> iterable = categoryRepository.findAll();
        List<CategoryDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
    }



    public List<CategoryDTO> getByLanguage(Language language){

        List<CategoryDTO> list = new LinkedList<>();
        categoryRepository.getAllByVisibleTrue().forEach(entity -> {
            list.add(getByLanguageLogica(entity,language));
        });
        return list;
    }

    public CategoryDTO getByLanguageLogica(CategoryEntity entity, Language language){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        switch (language){
            case uz -> dto.setName(entity.getName_uz());
            case en -> dto.setName(entity.getName_en());
            case ru -> dto.setName(entity.getName_ru());
            default -> dto.setName(entity.getName_uz());
        }
        return dto;
    }





    public CategoryDTO toDTO(CategoryEntity entity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }
}
