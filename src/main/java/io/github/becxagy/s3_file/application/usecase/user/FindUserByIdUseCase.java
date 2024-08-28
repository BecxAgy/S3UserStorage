package io.github.becxagy.s3_file.application.usecase.user;

import io.github.becxagy.s3_file.domain.User;

public interface FindUserByIdUseCase {
    User findById (Long id);
}
