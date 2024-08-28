package io.github.becxagy.s3_file.application.usecase.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageUseCase {
    String uploadFile(MultipartFile file);
    void deleteFile(String fileName);
}
