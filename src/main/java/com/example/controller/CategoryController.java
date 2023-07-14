package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CategoryDTO dto){
        return new ResponseEntity<>(categoryService.add(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody CategoryDTO dto){
        boolean b = categoryService.updateById(id,dto);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        Boolean result = categoryService.deleteById(id);
        if (result){
            return ResponseEntity.ok("Category deleted!!!");
        }
        return ResponseEntity.badRequest().body("Category not faund");
    }

    @GetMapping("/all")
    public List<CategoryDTO> all(){
        return categoryService.getAll();
    }
}
