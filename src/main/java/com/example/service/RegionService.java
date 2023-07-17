package com.example.service;

import com.example.Enum.Language;
import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO add(RegionDTO dto) {

        RegionEntity entity = new RegionEntity();
        entity.setOrder_number(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setCreated_date(LocalDateTime.now());
        regionRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());

        return dto;
    }

    public Boolean updateById(Integer id, RegionDTO dto){
        int effect = regionRepository.updateById(id, dto.getOrder_number(), dto.getName_uz(), dto.getName_ru(), dto.getName_en());
        return effect == 1;
    }


    public Boolean deleteById(Integer id){
        Optional<RegionEntity> optional = regionRepository.getById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Region not faund");
        }
        optional.ifPresent(regionRepository::delete);
        return true;
    }

    public List<RegionDTO> getAll(){
        Iterable<RegionEntity> iterable = regionRepository.findAll();
        List<RegionDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
    }




    public List<RegionDTO> getByLanguage(Language lang){
        List<RegionDTO> dtoList = new LinkedList<>();
        regionRepository.findAllByVisibleTrue().forEach(entity -> {
            dtoList.add(getByLangLogica(entity,lang));
        });
        return dtoList;
    }


    public RegionDTO getByLangLogica(RegionEntity entity, Language lang){
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        switch (lang){
            case en -> dto.setName(entity.getName_en());
            case uz -> dto.setName(entity.getName_uz());
            case ru -> dto.setName(entity.getName_ru());
            default -> dto.setName(entity.getName_uz());
        }
        return dto;
    }



    public RegionDTO toDTO(RegionEntity entity){
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrder_number(entity.getOrder_number());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

}
