package com.example.controller;

import com.example.dto.ArticleSavedDTO;
import com.example.service.ArticleSavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/articleSaved")
public class ArticleSavedController {

    @Autowired
    private ArticleSavedService articleSavedService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody ArticleSavedDTO dto){
        return new ResponseEntity<>(articleSavedService.add(dto), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteByArticleId(@PathVariable("id") String id){
        Boolean result = articleSavedService.deleteByArticleId(id);
        if (result){
            return ResponseEntity.ok("Article deleted");
        }
        return ResponseEntity.badRequest().body("Article not faund");
    }

    @GetMapping("/getSavedArticleList")
    public ResponseEntity<?> getAllSavedArticle(){
        return ResponseEntity.ok(articleSavedService.getAllSavedArticle());
    }

}
