package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.ArticleDTO;
import com.example.dto.ArticleLikeDTO;
import com.example.dto.JwtDTO;
import com.example.entity.ArticleEntity;
import com.example.service.ArticleLikeService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/articleLike")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody ArticleLikeDTO dto,
                                  HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(articleLikeService.like(dto, jwtDTO.getId()));
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> dislike(@RequestBody ArticleLikeDTO dto,
                                  HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
       return ResponseEntity.ok(articleLikeService.dislike(dto,jwtDTO.getId()));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody ArticleLikeDTO dto,
                                     HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(articleLikeService.remove(dto,jwtDTO.getId()));
    }
}
