package com.example.service;

import com.example.dto.*;
import com.example.entity.CommentEntity;
import com.example.exaption.AppBadRequestException;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.ArticleRepository;
import com.example.repository.CastomProfileRepository;
import com.example.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CastomProfileRepository castomProfileRepository;

    public CommentDTO add(CommentDTO dto, Integer profileId){
        check(dto);{
            CommentEntity entity = new CommentEntity();
            entity.setContent(dto.getContent());
            entity.setArticle_id(dto.getArticle_id());
            entity.setReply_id(dto.getReply_id());
            entity.setCreated_date(dto.getCreated_date());
            entity.setProfile_id(profileId);
            commentRepository.save(entity);
            dto.setProfile_id(entity.getProfile_id());
            dto.setCreated_date(entity.getCreated_date());
            dto.setId(entity.getId());
            dto.setProfile_id(entity.getProfile_id());

            return dto;
        }
    }


    public Boolean update(Integer id,CommentDTO dto){
        int effect = commentRepository.update(dto.getContent(),id);
        return effect == 1;
    }

    public Boolean delete(Integer id){
        Optional<CommentEntity> optional = commentRepository.getById(id);
        if (optional.isEmpty()){
            throw new ItemNotFoundException("Comment not faund");
        }
        optional.ifPresent(commentRepository::delete);
        return true;
    }


    public PageImpl<CommentDTO> commentPagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id"));
        Page<CommentEntity> pageObj = commentRepository.findAll(pageable);
        List<CommentDTO> list = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(list,pageable,pageObj.getTotalElements());
    }



    public CommentDTO toDTO(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setReply_id(entity.getReply_id());
        dto.setUpdate_date(entity.getUpdate_date());
        dto.setProfile_id(entity.getProfile_id());
        dto.setArticle_id(entity.getArticle_id());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public PageImpl<CommentDTO> filter(CommentFilterDTO filterDTO, int page, int size){
        FilterResultDTO<CommentEntity> result = castomProfileRepository.commentFilter(filterDTO,page, size);
        List<CommentDTO> list = result.getContent().stream().map(this::toDTO).collect(Collectors.toList());

        return new PageImpl<>(list,PageRequest.of(page,size),result.getTotalCount());
    }

    private boolean check(CommentDTO commentDTO) {
        if (commentDTO.getContent() == null) {
            throw new AppBadRequestException("Content is null!");
        }
        if (commentDTO.getArticle_id() == null) {
            throw new AppBadRequestException("Article is null!");
        }
        return true;
    }

}
