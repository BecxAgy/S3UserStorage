package io.github.becxagy.s3_file.adapter.jpapersistence.user;

import io.github.becxagy.s3_file.application.port.persistence.user.WriteUserPort;
import io.github.becxagy.s3_file.domain.User;

import java.util.Optional;

public class WriteUserAdapter implements WriteUserPort {
    @Override
    public User saveNew(User domain) {
        return null;
    }

    @Override
    public Optional<User> update(User domain) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
