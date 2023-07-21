package com.example.controller;

import com.example.Enum.Language;
import com.example.Enum.ProfileRole;
import com.example.dto.JwtDTO;
import com.example.dto.RegionDTO;
import com.example.service.RegionService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping(value = "/admin/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO dto,
                                    HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        return new ResponseEntity<>(regionService.add(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody RegionDTO regionDTO,
                                        HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.updateById(id,regionDTO));
    }

    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        Boolean result = regionService.deleteById(id);
        if (result){
         return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not faund");
    }

    @GetMapping("/admin/all")
    public List<RegionDTO> all(HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        return regionService.getAll();
    }


    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getBylang(@RequestParam(value = "lang",
                                                     defaultValue = "ru")Language language) {
        return ResponseEntity.ok(regionService.getByLanguage(language));
    }
}
