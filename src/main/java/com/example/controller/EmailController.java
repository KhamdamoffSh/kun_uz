package com.example.controller;

import com.example.Enum.ProfileRole;
import com.example.dto.ApiResponseDTO;
import com.example.dto.EmailDTO;
import com.example.dto.JwtDTO;
import com.example.service.EmailService;
import com.example.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get/email/history/{email}")
    public ResponseEntity<List<EmailDTO>> getByEmailHistory(@Valid  @PathVariable("email") String email){
        return ResponseEntity.ok(emailService.getByEmailHistory(email));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<EmailDTO>> getAll(){
        return ResponseEntity.ok(emailService.getAll());
    }


    /*@GetMapping(value = "/get/given/date/{date}")
    public ResponseEntity<List<EmailDTO>> getByGivenDate(@PathVariable("date") LocalDate  date){
        return ResponseEntity.ok(emailService.getByGivenDate(date));
    }*/


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin/pagination")
    public ResponseEntity<PageImpl<EmailDTO>> pagination(@RequestParam(value = "page",defaultValue = "1")int page,
                                                         @RequestParam(value = "size",defaultValue = "10")int size,
                                                         HttpServletRequest request){
        PageImpl<EmailDTO> pagination = emailService.emailPagination(page-1,size);
        return ResponseEntity.ok(pagination);
    }

}
