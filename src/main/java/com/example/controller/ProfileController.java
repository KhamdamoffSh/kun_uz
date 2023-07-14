package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {
        return new ResponseEntity<>(profileService.add(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody ProfileDTO profileDTO) {
        Boolean b = profileService.updateById(id, profileDTO);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> paginationProfileList(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> paginationList = profileService.profilePagination(page - 1, size);
        return ResponseEntity.ok(paginationList);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        Boolean result = profileService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Profile deleted!!!");
        }
        return ResponseEntity.badRequest().body("Profile not faund");
    }

    @GetMapping("/all")
    public List<ProfileDTO> all(){
        return profileService.getAll();
    }


    @PostMapping(value = "/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestBody ProfileFilterDTO filterDTO,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> response = profileService.filter(filterDTO, page - 1, size);
        return ResponseEntity.ok(response);
    }


}
