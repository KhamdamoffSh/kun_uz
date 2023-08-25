package com.example.service;

import com.example.Enum.ProfileRole;
import com.example.Enum.ProfileStatus;
import com.example.dto.*;
import com.example.entity.ProfileEntity;
import com.example.exaption.AppBadRequestException;
import com.example.repository.ProfileRepository;
import com.example.util.JwtUtil;
import com.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private  MailSenderService mailSenderService;
    @Value("${server.url}")
    private String serverUrl;

    public ApiResponseDTO login(AuthDTO dto) {
        // check
        Optional<ProfileEntity> optional = profileRepository.findByPhone(dto.getPhone());
        if (optional.isEmpty()) {
            return new ApiResponseDTO(false, "Login or Password not found");
        }
        ProfileEntity profileEntity = optional.get();
        if (!profileEntity.getPassword().equals(MD5Util.encode(dto.getPassword()))) {
            return new ApiResponseDTO(false, "Login or Password not found");
        }
        if (!profileEntity.getStatus().equals(ProfileStatus.ACTIVE) || !profileEntity.getVisible()) {
            return new ApiResponseDTO(false, "Your status not active. Please contact with support.");
        }

        ProfileDTO response = new ProfileDTO();
        response.setName(profileEntity.getName());
        response.setSurname(profileEntity.getSurname());
        response.setRole(profileEntity.getRole());
        response.setPhone(profileEntity.getPhone());
        response.setJwt(JwtUtil.encode(profileEntity.getPhone(), profileEntity.getRole()));
        return new ApiResponseDTO(true, response);
    }

    public ApiResponseDTO registration(RegistrationDTO dto) {
        // check
        Optional<ProfileEntity> exists = profileRepository.findByEmail(dto.getEmail());
        if (exists.isPresent()) {
            if (exists.get().getStatus().equals(ProfileStatus.REGISTRATION)){
                profileRepository.delete(exists.get());
            }else {
                return new ApiResponseDTO(false, "Email already exists.");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.encode(dto.getPassword()));
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.REGISTRATION);
        profileRepository.save(entity);
        mailSenderService.sendEmailVerification(dto.getEmail(), entity.getName(), entity.getId());
        String jwt = JwtUtil.encodeEmailJwt(entity.getId());

        String url = serverUrl + "/api/v1/auth/verification/email/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Kun uz registration compilation",
                "To complete your registration please click following link: " + url);
        return new ApiResponseDTO(true, "The verification link was send to email.");
    }

    public ApiResponseDTO emailVerification(String jwt) {
        JwtDTO jwtDTO = JwtUtil.decodeEmailJwt(jwt);

        Optional<ProfileEntity> exists = profileRepository.findById(jwtDTO.getId());
        if (exists.isEmpty()) {
            throw new AppBadRequestException("Profile not found");
        }

        ProfileEntity entity = exists.get();
        if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadRequestException("Wrong status");
        }
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity); // update
        return new ApiResponseDTO(true, "Registration completed");
    }
}
