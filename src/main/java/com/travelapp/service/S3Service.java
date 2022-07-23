package com.travelapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    void uploadFile(MultipartFile file);

    byte[] getFromS3(String path, String key);
}
