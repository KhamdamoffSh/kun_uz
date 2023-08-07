package com.example.controller;

import com.example.Enum.Language;
import com.example.Enum.ProfileRole;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.JwtDTO;
import com.example.service.ArticleTypeService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/create")
    public ResponseEntity<?> create(@Valid @RequestBody ArticleTypeDTO dto,
                                    HttpServletRequest request){
        return new ResponseEntity<>(articleTypeService.add(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateById(@Valid @PathVariable("id") Integer id,
                                        @RequestBody ArticleTypeDTO dto,
                                        HttpServletRequest request){
        return ResponseEntity.ok(articleTypeService.updateById(id,dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        Boolean result = articleTypeService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("ArticleType deleted!!!");
        }
        return ResponseEntity.badRequest().body("ArticleType not faund");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public List<ArticleTypeDTO> getAll(HttpServletRequest request){
        return articleTypeService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLang(@RequestParam(value = "lang",
            defaultValue = "uz") Language language) {
        return ResponseEntity.ok(articleTypeService.getByLanguage(language));
    }
}
