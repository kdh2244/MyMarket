package com.example.market.Controller;

import com.example.market.dto.PresignedUrlResponse;
import com.example.market.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    @Autowired
    S3Service s3Service;

    @GetMapping("/presigned-url")
    public ResponseEntity<PresignedUrlResponse> getPresignedUrl(
            @RequestParam("fileName") String fileName){
        PresignedUrlResponse presignedUrlResponse = s3Service.getPresignedUrl(fileName);
        return ResponseEntity.ok(presignedUrlResponse);
    }

}
