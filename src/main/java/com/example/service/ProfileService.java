package com.example.service;

import com.example.Enum.ProfileRole;
import com.example.Enum.ProfileStatus;
import com.example.dto.FilterResultDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.entity.ProfileEntity;
import com.example.exaption.AppBadRequestException;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.CastomProfileRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CastomProfileRepository castomProfileRepository;

    public ProfileDTO add(ProfileDTO dto){
        check(dto);

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setRole(dto.getRole());
        entity.setCreated_date(LocalDateTime.now());
        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }


    public Boolean updateById(Integer id, ProfileDTO dto){
        check(dto);

        int effectedRows = profileRepository.updatebyId(id, dto.getName(), dto.getSurname(), dto.getEmail(), dto.getPhone(), dto.getPassword());
            return effectedRows == 1;
        }


        public PageImpl<ProfileDTO> profilePagination(int page, int size){
            Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id"));
            Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);
            List<ProfileDTO> list = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
            return new PageImpl<>(list,pageable,pageObj.getTotalElements());
        }



        public Boolean deleteById(Integer id){
            Optional<ProfileEntity> optional = profileRepository.getById(id);
            if (optional.isEmpty()){
                throw new ItemNotFoundException("profile not faund");
            }
            optional.ifPresent(profileRepository::delete);
            return true;
        }

        public List<ProfileDTO> getAll(){
        Iterable<ProfileEntity> iterable = profileRepository.findAll();
        List<ProfileDTO> list = new LinkedList<>();
        iterable.forEach(i -> {
            list.add(toDTO(i));
        });
        return list;
        }



        public PageImpl<ProfileDTO> filter(ProfileFilterDTO filterDTO,int page,int size){
            FilterResultDTO<ProfileEntity> result = castomProfileRepository.filter(filterDTO,page, size);
            List<ProfileDTO> list = result.getContent().stream().map(this::toDTO).collect(Collectors.toList());

            return new PageImpl<>(list,PageRequest.of(page,size),result.getTotalCount());
        }



    private void check (ProfileDTO profileDTO) throws AppBadRequestException {
        if (profileDTO.getName() == null || profileDTO.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (profileDTO.getSurname() == null || profileDTO.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
        if (profileDTO.getEmail() == null || profileDTO.getEmail().isBlank()) {
            throw new AppBadRequestException("email qani?");
        }
        if (profileDTO.getPhone() == null || profileDTO.getPhone().isBlank()) {
            throw new AppBadRequestException("phone qani?");
        }
        if (profileDTO.getPassword() == null || profileDTO.getPassword().isBlank() && profileDTO.getPassword().length() > 5) {
            throw new AppBadRequestException("password qani?");
        }
    }


    public ProfileDTO toDTO(ProfileEntity entity){
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setCreated_date(entity.getCreated_date());
        dto.setPhoto_id(entity.getPhoto_id());
        return dto;
    }

}
