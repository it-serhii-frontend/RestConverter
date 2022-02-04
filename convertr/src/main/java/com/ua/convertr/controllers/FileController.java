package com.ua.convertr.controllers;

import com.ua.convertr.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity uploadFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            for (MultipartFile file: files) {
                fileService.save(file);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Successfully upload!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Unfortunately failed to upload!");
        }
    }

    @GetMapping("/uploads/{multipartFileName:.+}")
    public ResponseEntity getFile(@PathVariable String multipartFileName) throws IOException {
        Resource resource = fileService.convert(multipartFileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}
