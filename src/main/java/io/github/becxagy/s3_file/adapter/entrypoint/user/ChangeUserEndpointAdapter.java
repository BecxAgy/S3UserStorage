package io.github.becxagy.s3_file.adapter.entrypoint.user;

import io.github.becxagy.s3_file.adapter.entrypoint.mapper.user.UserRestMapper;
import io.github.becxagy.s3_file.adapter.entrypoint.model.UserDto;
import io.github.becxagy.s3_file.application.port.entrypoint.user.ChangeUserEndepointPort;
import io.github.becxagy.s3_file.application.usecase.storage.StorageUseCase;
import io.github.becxagy.s3_file.application.usecase.user.CreateUserUseCase;
import io.github.becxagy.s3_file.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeUserEndpointAdapter implements ChangeUserEndepointPort {
    @Autowired
    private StorageUseCase storageUseCase;
    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private UserRestMapper userRestMapper;


    @Override
    public UserDto save(UserDto t) {
        //use mapper for convert to domain
        User userDomain = userRestMapper.mapToDomain(t);

        String imageUrl = storageUseCase.uploadFile(t.image());
        System.out.println(imageUrl);
        userDomain.setImageUrl(imageUrl);
        //call use case save method in try catch

        createUserUseCase.create(userDomain);
    return null;
    }

    @Override
    public UserDto updateById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
