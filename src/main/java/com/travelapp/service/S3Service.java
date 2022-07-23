package com.travelapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file);

    byte[] getFromS3(String key);
}
