package com.travelapp.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.travelapp.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

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
    public void uploadFile(MultipartFile img) {
        try {
            File file = mapMultipartFileToFile(img);
            String fileName = "TEST_" + img.getOriginalFilename();
            this.s3Client.putObject(new PutObjectRequest(this.bucketName, fileName, file));
            file.delete();
        } catch (AmazonServiceException err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public byte[] getFromS3(String path, String key) {
        try {
            S3Object object = this.s3Client.getObject(new GetObjectRequest(this.bucketName, "TEST_рен.jpg"));
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
