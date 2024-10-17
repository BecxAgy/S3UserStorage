package io.github.becxagy.s3_file.adapter.storage;

import java.io.File;

import java.io.IOException;
import java.time.LocalDateTime;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import io.github.becxagy.s3_file.application.port.StoragePort;
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
            System.out.println("Error while deleting file: " + e.getMessage());
        }
    }

    @Override
    @Async
    public String upload(final MultipartFile multipartFile) {
        try {
            final File file = convertMultiPartFileToFile(multipartFile);
            String fileUrl = uploadFileToS3Bucket(bucketName, file);
            file.delete();
            return fileUrl;
        } catch (final AmazonServiceException ex) {
            System.out.println("Error while uploading file: " + ex.getMessage());
        }
        return "";
    }

    // Método para redimensionar a imagem antes do upload
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics.dispose(); // Libera os recursos do Graphics2D
        return resizedImage;
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        File file = null;
        try {
            BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());

            //Definindo novo tamanho
            int targetWidth = originalImage.getWidth() / 2;
            int targetHeight = originalImage.getHeight() / 2;

            BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);

            file = new File(multipartFile.getOriginalFilename());
            ImageIO.write(resizedImage, "jpg", file);

        } catch (final IOException ex) {
            System.out.println("Error while converting and resizing multipart file: " + ex.getMessage());
        }
        return file;
    }

    @Transactional
    private String uploadFileToS3Bucket(final String bucketName, final File file) {
        final String uniqueFileName = LocalDateTime.now() + "_" + file.getName();
        final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uniqueFileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead); //Tornando o arquivo publicamente acessivel

        s3Client.putObject(putObjectRequest);

        // Retorna a URL do arquivo armazenado
        return s3Client.getUrl(bucketName, uniqueFileName).toString();
    }
}
