package io.github.becxagy.s3_file.adapter.entrypoint.model;

import org.springframework.web.multipart.MultipartFile;

public record UserDto (
     MultipartFile image,
     String name,
     String email
){}
