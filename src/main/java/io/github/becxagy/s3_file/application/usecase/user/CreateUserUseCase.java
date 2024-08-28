package io.github.becxagy.s3_file.application.usecase.user;

import io.github.becxagy.s3_file.domain.User;

public interface CreateUserUseCase {
    void create(User user);
}
