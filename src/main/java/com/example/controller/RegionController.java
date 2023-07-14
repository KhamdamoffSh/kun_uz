package com.example.controller;

import com.example.dto.RegionDTO;
import com.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody RegionDTO dto){
        return new ResponseEntity<>(regionService.add(dto), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer id,
                                        @RequestBody RegionDTO regionDTO){
        Boolean b = regionService.updateById(id,regionDTO);
        return ResponseEntity.ok(b);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        Boolean result = regionService.deleteById(id);
        if (result){
         return ResponseEntity.ok("Region deleted!!!");
        }
        return ResponseEntity.badRequest().body("Region not faund");
    }

    @GetMapping("/all")
    public List<RegionDTO> all(){
        return regionService.getAll();
    }

    // TODO
    @GetMapping("/uz")
    public List<RegionDTO> getBylanguageUz() {
        return regionService.getByLanguageUz();
    }
}
