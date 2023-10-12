package com.example.api.controllers;

import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {

    private String angularPath = System.getProperty("user.dir") + "/../client/src";
    private UUID uuid = UUID.randomUUID();

    @PostMapping("/save")
    public ResponseEntity<Object> saveImage(@RequestParam("file") MultipartFile file) {
        try {

            if (file == null) {
                throw new Exception("File not found");
            }

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            String finalPath = "/assets/" + uuid + fileName;
            String filePath = angularPath + finalPath;
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(file.getBytes());
            fos.close();

            return ResponseHandler.responseBuilder("Image saved successfull", HttpStatus.CREATED, finalPath);
        } catch (Exception e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
