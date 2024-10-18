package io.github.becxagy.s3_file.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import io.github.becxagy.s3_file.shared.exceptions.FileConversionException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

public class FileUtil {

    public static File convertMultipartFileToFile(final MultipartFile multipartFile) {
        try {
            BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());

            int targetWidth = originalImage.getWidth() / 2;
            int targetHeight = originalImage.getHeight() / 2;

            BufferedImage resizedImage = resizeImage(originalImage, targetWidth, targetHeight);

            File file = new File(multipartFile.getOriginalFilename());
            ImageIO.write(resizedImage, "jpg", file);
            return file;

        } catch (IOException e) {
            throw new FileConversionException("Error converting MultipartFile to File: " + e.getMessage());
        }
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics.dispose();
        return resizedImage;
    }
}
