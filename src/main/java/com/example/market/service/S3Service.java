package com.example.market.service;

import com.example.market.dto.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Autowired
    private S3Presigner s3Presigner;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    public PresignedUrlResponse getPresignedUrl(String originalFileName){

        String s3FileName = "products/" + UUID.randomUUID() + "_" + originalFileName;

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(s3FileName)
                .build();

        //10분간 유효한 Presigned PUT URL 생성
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(r ->
                r.signatureDuration(Duration.ofMinutes(10))
                        .putObjectRequest(objectRequest)
        );

        String uploadUrl = presignedRequest.url().toString();
        String imageUrl = String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucket,region,s3FileName);

        return new PresignedUrlResponse(uploadUrl,imageUrl);
    }

}
