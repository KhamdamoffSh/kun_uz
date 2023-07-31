package com.example.service;

import com.example.dto.ApiResponseDTO;
import com.example.dto.EmailDTO;
import com.example.entity.EmailEntity;
import com.example.exaption.ItemNotFoundException;
import com.example.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Value("${server.url}")
    private String serverUrl;

    public ApiResponseDTO create(EmailDTO dto) {

        EmailEntity entity = new EmailEntity();
        entity.setEmail(dto.getEmail());
        entity.setMessage(dto.getMessage());
        emailRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreated_date(entity.getCreated_date());

        mailSenderService.sendEmail(dto.getEmail(), "Kun uz registration compilation",
                "Bu kun uz saytining tekshiruv email habarnomasi");
        return new ApiResponseDTO(true, "Sent message to email");

    }

    public List<EmailDTO> getByEmailHistory(String email) {
        List<EmailDTO> list = new LinkedList<>();
        Optional<EmailEntity> optional = emailRepository.getByEmail(email);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Email not faund");
        }
        optional.stream().forEach(e -> {
            list.add(toDTO(optional.get()));
        });
        return list;
    }

    public List<EmailDTO> getAll() {
        List<EmailDTO> list = new LinkedList<>();
        Iterable<EmailEntity> optional = emailRepository.findAll();
        optional.forEach(e -> {
            list.add(toDTO(e));
        });
        return list;
    }


   /* public List<EmailDTO> getByGivenDate(LocalDate date) {
        List<EmailDTO> list = new LinkedList<>();
        Iterable<EmailEntity> iterable= emailRepository.findAllByCreated_date();
        iterable.forEach(e ->{
            list.add(toDTO(e));
        });
        return list;
    }*/


    public PageImpl<EmailDTO> emailPagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id"));
        Page<EmailEntity> pageObj = emailRepository.findAll(pageable);
        List<EmailDTO> list = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(list,pageable,pageObj.getTotalElements());
    }


    public EmailDTO toDTO(EmailEntity entity) {
        EmailDTO dto = new EmailDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setMessage(entity.getMessage());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }
}
