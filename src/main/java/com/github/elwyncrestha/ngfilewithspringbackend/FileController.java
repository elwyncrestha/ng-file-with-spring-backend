package com.github.elwyncrestha.ngfilewithspringbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Elvin Shrestha on 8/22/2020
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file.upload.path}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file,
        @RequestParam String fileName) {
        String uploaded = FileUtils.upload(file, fileName, this.path);

        if (null == uploaded) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok(new Response(uploaded));
    }
}
