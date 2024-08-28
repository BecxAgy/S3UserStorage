package io.github.becxagy.s3_file.adapter.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import io.github.becxagy.s3_file.application.port.StoragePort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class StorageS3Adapter implements StoragePort {

    private final AmazonS3 s3Client;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    public StorageS3Adapter(AmazonS3 client) {
        this.s3Client = client;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    @Async
    public String upload(final MultipartFile multipartFile) {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            uploadFileToS3Bucket(bucketName, file);
            file.delete();
            return multipartFile.getOriginalFilename();
        } catch (final AmazonServiceException ex) {
         System.out.println("Error while uploading file: " + ex.getMessage());
        }
        return "";
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
           System.out.println("Error while converting multipart file: " + ex.getMessage());
        }
        return file;
    }

    private void uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file);
        s3Client.putObject(putObjectRequest);
    }
}
