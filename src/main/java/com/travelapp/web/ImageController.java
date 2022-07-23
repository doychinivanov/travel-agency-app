package com.travelapp.web;

import com.amazonaws.util.IOUtils;
import com.travelapp.service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private S3Service s3Service;

    public ImageController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping("/images")
    public void getImage(HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");

        InputStream is = new ByteArrayInputStream(this.s3Service.getFromS3("TEST_60575496_303.jpg"));
        IOUtils.copy(is, response.getOutputStream());
    }
}
