package io.github.becxagy.s3_file.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileConversionException extends ProjectException {
    public FileConversionException(String message) {
        super(400,message);
    }
}