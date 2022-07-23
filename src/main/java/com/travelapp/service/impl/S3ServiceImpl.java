package com.travelapp.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.travelapp.service.S3Service;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {
    @Value("${cloud.aws.application.bucket.name}")
    private String bucketName;
    private AmazonS3 s3Client;

    @Autowired
    public S3ServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String uploadFile(MultipartFile img) {
        try {
            File file = mapMultipartFileToFile(img);
            String fileName = String.format("%s.%s", UUID.randomUUID().toString(), FilenameUtils.getExtension(file.getAbsolutePath()));
            this.s3Client.putObject(new PutObjectRequest(this.bucketName, fileName, file));
            file.delete();
            return fileName;
        } catch (AmazonServiceException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }

    @Override
    public byte[] getFromS3(String key) {
        try {
            S3Object object = this.s3Client.getObject(new GetObjectRequest(this.bucketName, key));
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }

    private File mapMultipartFileToFile(MultipartFile img) {
        File mappedFile = new File(Objects.requireNonNull(img.getOriginalFilename()));

        try {
            FileOutputStream fos = new FileOutputStream(mappedFile);
            fos.write(img.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return mappedFile;
    }
}
