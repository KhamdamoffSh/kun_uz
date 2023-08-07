package com.example.controller;

import com.example.Enum.Language;
import com.example.Enum.ProfileRole;
import com.example.dto.JwtDTO;
import com.example.dto.RegionDTO;
import com.example.service.RegionService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/admin/create")
    public ResponseEntity<?> create(@Valid  @RequestBody RegionDTO dto,
                                    HttpServletRequest request){
        return new ResponseEntity<>(regionService.add(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<?> updateById(@Valid @PathVariable("id") Integer id,
                                        @RequestBody RegionDTO regionDTO,
                                        HttpServletRequest request){
        return ResponseEntity.ok(regionService.updateById(id,regionDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request){
        Boolean result = regionService.deleteById(id);
        if (result){
         return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not faund");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public List<RegionDTO> all(HttpServletRequest request){
        return regionService.getAll();
    }


    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getBylang(@RequestParam(value = "lang",
                                                     defaultValue = "ru")Language language) {
        return ResponseEntity.ok(regionService.getByLanguage(language));
    }
}
