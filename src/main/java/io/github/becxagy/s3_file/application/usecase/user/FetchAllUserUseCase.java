package io.github.becxagy.s3_file.application.usecase.user;

import io.github.becxagy.s3_file.domain.User;

import java.util.Collection;

public interface FetchAllUserUseCase {
    Collection<User> fetchAll();
}
