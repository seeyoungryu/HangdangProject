package com.example.hangdang.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final UploadService s3Service;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadImage(MultipartFile file) throws IllegalAccessException {
        String fileName = createFileName(file.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try (InputStream inputStream = file.getInputStream()) {
            s3Service.uploadFile(inputStream, objectMetadata, fileName);
        } catch(IOException e) {
            throw new IllegalAccessException(String.format("파일 변환 중에러가 발생하였습니다.(%s)", file.getOriginalFilename()));
        }
        return s3Service.getFileUrl(fileName);
    }

    //기존 확장자명을 유지한 채, 유니크한 파일의 이름을 생성하는 로직
    private String createFileName(String originalFileName) throws IllegalAccessException {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFileName));
    }

    //파일의 확장자명을 가져오는 로직
    private String getFileExtension(String fileName) throws IllegalAccessException {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalAccessException(String.format("잘못된 형식의 파일($s) 입니다", fileName));
        }
    }
}
