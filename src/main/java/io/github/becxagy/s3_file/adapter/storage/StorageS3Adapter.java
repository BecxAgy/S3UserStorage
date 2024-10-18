package io.github.becxagy.s3_file.adapter.storage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import io.github.becxagy.s3_file.util.FileUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import io.github.becxagy.s3_file.application.port.StoragePort;
import io.github.becxagy.s3_file.shared.exceptions.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
        try {
            s3Client.deleteObject(bucketName, key);
            System.out.println("File deleted successfully: " + key);
        } catch (AmazonServiceException e) {

            throw new S3ServiceException("Error while deleting file from S3: " + e.getMessage());
        } catch (Exception e) {

            throw new FileNotFoundException("File not found: " + key);
        }
    }

    @Override
    @Async
    public String upload(final MultipartFile multipartFile) {
        try {

            final File file = FileUtil.convertMultipartFileToFile(multipartFile);
            String fileUrl = uploadFileToS3Bucket(bucketName, file);
            file.delete();
            return fileUrl;
        } catch (final AmazonServiceException ex) {

            throw new S3ServiceException("Error while uploading file to S3: " + ex.getMessage());
        }
    }


    @Transactional
    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        try {
            final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
            final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead); // Tornando o arquivo publicamente acessível

            s3Client.putObject(putObjectRequest);

            // Retorna a URL do arquivo armazenado
            return s3Client.getUrl(bucketName, uniqueFileName).toString();
        } catch (AmazonServiceException ex) {

            throw new TransactionFailedException("Failed to upload file to S3: " + ex.getMessage());
        }
    }
}
