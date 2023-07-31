package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.*;
import com.example.service.CommentService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CommentDTO dto,
                                    HttpServletRequest request){
        JwtDTO jwtDTO = SecurityUtil.hasRole(request, null);
        return new ResponseEntity<>(commentService.add(dto,jwtDTO.getId()), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        Boolean result = commentService.delete(id);
        if (result){
            return ResponseEntity.ok("Comment delete");
        }
        return ResponseEntity.badRequest().body("Comment not faund");
    }

    @GetMapping(value = "/pagination")
    public ResponseEntity<PageImpl<CommentDTO>> pagination(@RequestParam(value = "page",defaultValue = "1")int page,
                                                         @RequestParam(value = "size",defaultValue = "10")int size){
        PageImpl<CommentDTO> pagination = commentService.commentPagination(page-1,size);
        return ResponseEntity.ok(pagination);
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<PageImpl<CommentDTO>> filter(@RequestBody CommentFilterDTO filterDTO,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<CommentDTO> response = commentService.filter(filterDTO,page - 1, size);
        return ResponseEntity.ok(response);
    }
}
