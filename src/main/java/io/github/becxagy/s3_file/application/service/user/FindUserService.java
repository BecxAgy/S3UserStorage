package io.github.becxagy.s3_file.application.service.user;

import io.github.becxagy.s3_file.application.usecase.user.FetchAllUserUseCase;
import io.github.becxagy.s3_file.application.usecase.user.FindUserByIdUseCase;
import io.github.becxagy.s3_file.domain.User;

import java.util.Collection;
import java.util.List;

public class FindUserService implements FindUserByIdUseCase, FetchAllUserUseCase {
    @Override
    public Collection<User> fetchAll() {
        return List.of();
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
