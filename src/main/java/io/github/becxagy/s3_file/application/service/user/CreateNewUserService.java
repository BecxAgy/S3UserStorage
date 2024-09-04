package io.github.becxagy.s3_file.application.service.user;

import io.github.becxagy.s3_file.application.usecase.user.CreateUserUseCase;
import io.github.becxagy.s3_file.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUserService implements CreateUserUseCase {
    @Override
    public void create(User user) {

    }
}
