package com.example.thymeleaf.BlobStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/blob")
public class BlobStorageController {

    @Autowired
    private BlobStorageService blobStorageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String blobName = file.getOriginalFilename(); 
            return blobStorageService.uploadBlob(blobName, file.getInputStream(), file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file";
        }
    }
}