package com.example.hangdang.controller;

import com.example.hangdang.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {

    private final ImageService imageService;
    @PostMapping("/v1/upload")
    public String uploadImage(@RequestPart(value = "file")MultipartFile multipartFile) throws IllegalAccessException
    {
        return imageService.uploadImage(multipartFile);
    }
}
