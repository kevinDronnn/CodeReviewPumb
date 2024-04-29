package com.example.codereviewpumb.Controllers;

import com.example.codereviewpumb.Dto.AnimalDto;
import com.example.codereviewpumb.Mappers.AnimalMapper;
import com.example.codereviewpumb.Services.AnimalService;
import com.example.codereviewpumb.Services.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "AnimalController")
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AnimalController {

    @Autowired
    FileService fileService;

    @Autowired
    AnimalService animalService;

    @PostMapping("/files/uploads")
    public ResponseEntity<String> saveAnimalsFromFile(@RequestBody MultipartFile file) {
        if (file.getContentType().equals("application/xml") || file.getContentType().equals("text/csv")) {
            if (file.getContentType().equals("application/xml")) {
                fileService.readXmlFile(file);
            } else {
                fileService.readCsvFile(file);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).build();
    }

    @GetMapping("/getAnimalsByOptions")
    public ResponseEntity<List<AnimalDto>> getAnimalsByOptions(@RequestParam(name = "type", required = false) String type,
                                                               @RequestParam(name = "sex", required = false) String sex,
                                                               @RequestParam(name = "category", required = false) Byte category,
                                                               @RequestParam(name = "column", required = false) String column) {
        List<AnimalDto> animalDto = AnimalMapper.INSTANCE.animalEntityToAnimalDto(animalService.getAnimalsByParams(type, sex, category, column));
        return ResponseEntity.ok(animalDto);
    }

}
