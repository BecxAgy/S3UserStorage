package io.github.becxagy.s3_file.adapter.storage;

import io.github.becxagy.s3_file.application.usecase.storage.StorageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
    @Autowired
    private StorageUseCase storageUseCase;

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        storageUseCase.uploadFile(file);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity delete(@PathVariable("fileName" ) String filename){
        storageUseCase.deleteFile(filename);
        return ResponseEntity.ok().build() ;
    }


}
