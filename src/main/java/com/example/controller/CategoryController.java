package com.example.controller;

import com.example.Enum.Language;
import com.example.Enum.ProfileRole;
import com.example.dto.CategoryDTO;
import com.example.dto.JwtDTO;
import com.example.service.CategoryService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/admin/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto,
                                    HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        return new ResponseEntity<>(categoryService.add(dto), HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CategoryDTO dto,
                                    HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        boolean b = categoryService.updateById(id, dto);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping(value = "/admin/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        Boolean result = categoryService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Category deleted!!!");
        }
        return ResponseEntity.badRequest().body("Category not faund");
    }

    @GetMapping("/admin/all")
    public List<CategoryDTO> all(HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.ADMIN);
        return categoryService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<CategoryDTO>> getByLang(@RequestParam(value = "lang", defaultValue = "uz")
                                                       Language language) {
        return ResponseEntity.ok(categoryService.getByLanguage(language));
    }
}
