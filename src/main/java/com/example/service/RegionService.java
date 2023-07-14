package com.example.service;

import com.example.dto.RegionDTO;
import com.example.entity.RegionEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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


    // TODO
    public List<RegionDTO> getByLanguageUz(){
        List<RegionDTO> list = new LinkedList<>();
        Stream<Object> optional = regionRepository.getByName_uz()
                .stream().map(i ->{
                    return list.add(toDTO(i));
        });

        return list;
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
