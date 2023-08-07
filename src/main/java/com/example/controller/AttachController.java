package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.AttachDTO;
import com.example.dto.JwtDTO;
import com.example.service.AttachService;
import com.example.util.SecurityUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {

    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    public ResponseEntity<AttachDTO>upload(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok(attachService.upload(file));
    }
    @GetMapping(value = "/open/{id}/img", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] openById(@PathVariable String id){
        return attachService.openById(id);
    }
    //3. open general
    @GetMapping(value = "/open/{id}/general", produces = MediaType.ALL_VALUE)
    public byte[]openGeneral(@PathVariable String id){
        return attachService.openByIdGeneral(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/closed/pagination")
    public ResponseEntity<PageImpl<AttachDTO>>pagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "size", defaultValue ="10") Integer size,
                                                         HttpServletRequest request){
        return ResponseEntity.ok(attachService.pagination(page-1,size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/closed/{id}")
    public ResponseEntity<Boolean>deleteById(@PathVariable String id,
                                             HttpServletRequest request){
        return ResponseEntity.ok(attachService.deleteById(id));
    }
}
