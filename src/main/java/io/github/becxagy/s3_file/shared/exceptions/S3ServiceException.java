package io.github.becxagy.s3_file.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        HttpStatus.SERVICE_UNAVAILABLE)
public class S3ServiceException extends ProjectException {
    public S3ServiceException(String message) {
        super(503,message);
    }
}
