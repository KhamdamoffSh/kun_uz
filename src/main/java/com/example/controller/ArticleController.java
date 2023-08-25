package com.example.controller;

import com.example.dto.ArticleDTO;
import com.example.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.aspectj.util.LangUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping(value = "/moderator/create")
    public ResponseEntity<?> create(@Valid @RequestBody ArticleDTO dto) {
        return new ResponseEntity<>(articleService.add(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PutMapping(value = "/moderator/update/{id}")
    public ResponseEntity<?> updateById(@Valid @PathVariable("id") Integer id,
                                        @RequestBody ArticleDTO dto,
                                        HttpServletRequest request) {
        return ResponseEntity.ok(articleService.updateById(id, dto));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @DeleteMapping(value = "/moderator/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        Boolean result = articleService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Article deleted!!!");
        }
        return ResponseEntity.badRequest().body("Article not faund");
    }

    @PreAuthorize("hasAnyRole('MODERATOR','PUBLISHER')")
    @PutMapping(value = "/moderator/change/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(articleService.changeStatusById(id));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/moderator/getLastFiveArticle")
    private ResponseEntity<?> getLastFiveArticle(HttpServletRequest request) {
        return ResponseEntity.ok(articleService.lastFiveArticle(2));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/moderator/getLastThreeArticle")
    private ResponseEntity<?> getLastThreeArticle(HttpServletRequest request) {
        return ResponseEntity.ok(articleService.lastThreeArticle(2));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/moderator/notGivenId/{id}")
    private ResponseEntity<?> getNotGivenId(@PathVariable("id") String id,
                                           HttpServletRequest request){
        return ResponseEntity.ok(articleService.notGivenId(id));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("moderator/getIdAndLang/{id}")
    private ResponseEntity<?> getByIdAndLang(@PathVariable("id") Integer articleId,
                                             @RequestHeader(value = "Accepted-Language",defaultValue = "uz")LangUtil langUtil){
        return ResponseEntity.ok(articleService.getByIdAndLang(articleId,langUtil));
    }


    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("moderator/getLast4/{id}/{typeId}")
    private ResponseEntity<?> getLast4Article(@PathVariable("id") String articleId,
                                             @PathVariable("typeId") Integer typeId){
        return ResponseEntity.ok(articleService.getByLast4Article(articleId,typeId));
    }


    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("moderator/get4Most")
    private ResponseEntity<?> get4Most(){
        return ResponseEntity.ok(articleService.get4most());
    }


    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("moderator/getArticleTypeAndRegion/{articleType}/{regionId}")
    private ResponseEntity<?> getArticleTypeAndRegion(@PathVariable("articleType")String articleType,
                                                      @PathVariable("regionId") Integer regionId){
        return ResponseEntity.ok(articleService.getArticleTypeAndRegion(articleType,regionId));
    }
}
