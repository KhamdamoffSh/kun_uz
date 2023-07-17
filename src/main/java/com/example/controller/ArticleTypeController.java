package com.example.controller;

import com.example.Enum.Language;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegionDTO;
import com.example.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articleType")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ArticleTypeDTO dto){
        return new ResponseEntity<>(articleTypeService.add(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody ArticleTypeDTO dto){
        Boolean b = articleTypeService.updateById(id,dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        Boolean result = articleTypeService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("ArticleType deleted!!!");
        }
        return ResponseEntity.badRequest().body("ArticleType not faund");
    }

    @GetMapping("/all")
    public List<ArticleTypeDTO> getAll(){
        return articleTypeService.getAll();
    }

    @GetMapping("/lang")
    public ResponseEntity<List<ArticleTypeDTO>> getByLang(@RequestParam(value = "lang",
            defaultValue = "uz") Language language) {
        return ResponseEntity.ok(articleTypeService.getByLanguage(language));
    }
}
