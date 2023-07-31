package com.example.controller;

import com.example.dto.ArticleLikeDTO;
import com.example.dto.CommentLikeDTO;
import com.example.dto.JwtDTO;
import com.example.service.CommentLikeService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/commentLike")
public class CommentLikeController {

    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody CommentLikeDTO dto,
                                  HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(commentLikeService.like(dto, jwtDTO.getId()));
    }

    @PostMapping("/dislike")
    public ResponseEntity<?> dislike(@RequestBody CommentLikeDTO dto,
                                     HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(commentLikeService.dislike(dto,jwtDTO.getId()));
    }

    @PostMapping("/remove")
    public ResponseEntity<?> remove(@RequestBody CommentLikeDTO dto,
                                    HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request,null);
        return ResponseEntity.ok(commentLikeService.remove(dto,jwtDTO.getId()));
    }
}
