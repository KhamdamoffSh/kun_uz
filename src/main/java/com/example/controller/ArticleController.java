package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.ArticleDTO;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.JwtDTO;
import com.example.service.ArticleService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/moderator/create")
    public ResponseEntity<?> create(@RequestBody ArticleDTO dto,
                                    HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return new ResponseEntity<>(articleService.add(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/moderator/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody ArticleDTO dto,
                                        HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.updateById(id,dto));
    }


    @DeleteMapping(value = "/moderator/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                                        HttpServletRequest request) {
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, ProfileRole.MODERATOR);
        Boolean result = articleService.deleteById(id);
        if (result) {
            return ResponseEntity.ok("Article deleted!!!");
        }
        return ResponseEntity.badRequest().body("Article not faund");
    }

    @PutMapping(value = "/moderator/change/status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") Integer id,
                                          HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.changeStatusById(id));
    }


    @GetMapping("/moderator/getLastArticle")
    private ResponseEntity<?> getLastFiveArticle(){
        return ResponseEntity.ok(articleService.lastFiveArticle());
    }

}
