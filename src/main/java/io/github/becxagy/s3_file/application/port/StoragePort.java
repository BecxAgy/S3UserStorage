package io.github.becxagy.s3_file.application.port;

import org.springframework.web.multipart.MultipartFile;

public interface StoragePort {
    String upload( final MultipartFile file);
    void delete(String key);
}
