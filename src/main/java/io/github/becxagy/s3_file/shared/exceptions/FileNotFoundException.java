package io.github.becxagy.s3_file.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends ProjectException {
    public FileNotFoundException(String message) {
        super(404,message);
    }
}
