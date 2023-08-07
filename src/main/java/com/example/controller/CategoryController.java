package com.example.controller;

import com.example.Enum.Language;
import com.example.Enum.ProfileRole;
import com.example.dto.CategoryDTO;
import com.example.dto.JwtDTO;
import com.example.service.CategoryService;
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
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/admin/create")
    public ResponseEntity<?> create(@Valid  @RequestBody CategoryDTO dto,
                                    HttpServletRequest request) {
        return new ResponseEntity<>(categoryService.add(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable("id") Integer id,
                                    @RequestBody CategoryDTO dto,
                                    HttpServletRequest request) {
        boolean b = categoryService.updateById(id, dto);
        return ResponseEntity.ok(b);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        Boolean result = categoryService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Category deleted!!!");
        }
        return ResponseEntity.badRequest().body("Category not faund");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public List<CategoryDTO> all(HttpServletRequest request) {
        return categoryService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getByLang(@RequestParam(value = "lang", defaultValue = "uz")
                                                       Language language) {
        return ResponseEntity.ok(categoryService.getByLanguage(language));
    }
}
