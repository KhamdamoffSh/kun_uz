package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.*;
import com.example.service.AuthService;
import com.example.service.ProfileService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/admin/create")
    public ResponseEntity<?> create(@Valid  @RequestBody ProfileDTO dto, HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.add(dto, jwtDTO.getId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<?> updateById(@Valid @PathVariable("id") Integer id,
                                        @RequestBody ProfileDTO profileDTO,
                                        HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.updateById(id,profileDTO));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/detail")
    public ResponseEntity<Boolean> updateDetail(@RequestBody ProfileDTO dto,
                                                HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, null);
        return ResponseEntity.ok(profileService.updateDetail(jwtDTO.getId(), dto));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin/pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> paginationProfileList(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "10") int size,
                                                                      HttpServletRequest request) {
        PageImpl<ProfileDTO> paginationList = profileService.profilePagination(page - 1, size);
        return ResponseEntity.ok(paginationList);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        Boolean result = profileService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Profile deleted!!!");
        }
        return ResponseEntity.badRequest().body("Profile not faund");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public List<ProfileDTO> all(HttpServletRequest request) {
        return profileService.getAll();
    }


    @PostMapping(value = "/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestBody ProfileFilterDTO filterDTO,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> response = profileService.filter(filterDTO, page - 1, size);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = {"/login"})
    public ResponseEntity<ApiResponseDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }


}
