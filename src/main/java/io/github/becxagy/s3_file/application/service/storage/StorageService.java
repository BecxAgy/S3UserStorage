package io.github.becxagy.s3_file.application.service.storage;

import io.github.becxagy.s3_file.application.port.StoragePort;
import io.github.becxagy.s3_file.application.usecase.storage.StorageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service("storage")
public class StorageService implements StorageUseCase {
    @Autowired
    private  StoragePort storagePort;


    @Override
    public String uploadFile(MultipartFile file) {
        return storagePort.upload(file);
    }

    @Override
    public void deleteFile(String fileName) {

    }
}
