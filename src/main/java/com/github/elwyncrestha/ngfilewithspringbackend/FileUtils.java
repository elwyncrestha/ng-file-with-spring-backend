package com.github.elwyncrestha.ngfilewithspringbackend;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Elvin Shrestha on 8/22/2020
 */
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {}

    /**
     * Uploads the file with given name in provided path.
     *
     * @param file     A file to upload.
     * @param fileName The name of the file.
     * @param filePath The path where file is saved.
     * @return The name of the uploaded file, or null if error occurs.
     */
    public static String upload(MultipartFile file, String fileName, String filePath) {
        try {
            final byte[] bytes = file.getBytes();

            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                new File(filePath).mkdirs();
            }
            // check if file under same name exits, if exists delete it
            File dir = path.toFile();
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File f : files) {
                    // remove file if exists
                    if (f.getName().toLowerCase().contains(fileName.toLowerCase())) {
                        try {
                            f.delete();
                        } catch (Exception e) {
                            log.error("Failed to delete file {} {}", f.getName(), e.getMessage());
                            return null;
                        }
                    }
                }

            }
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename())
                .toLowerCase();
            String uploadTo = filePath + fileName + "." + fileExtension;

            path = Paths.get(uploadTo);
            Files.write(path, bytes);
            return fileName + "." + fileExtension;
        } catch (IOException e) {
            log.error("Error while saving file {}", e.getMessage());
            return null;
        }
    }
}
