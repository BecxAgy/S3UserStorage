package io.github.becxagy.s3_file.adapter.jpapersistence.user;

import io.github.becxagy.s3_file.application.port.persistence.user.ReadUserPort;
import io.github.becxagy.s3_file.domain.User;

import java.util.List;
import java.util.Optional;

public class ReadUserAdapter implements ReadUserPort {
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
