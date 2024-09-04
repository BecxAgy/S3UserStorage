package io.github.becxagy.s3_file.adapter.entrypoint.mapper.user;

import io.github.becxagy.s3_file.adapter.entrypoint.model.UserDto;
import io.github.becxagy.s3_file.domain.User;
import io.github.becxagy.s3_file.shared.RestConverter;
import org.springframework.stereotype.Component;

@Component
public class UserRestMapper implements RestConverter<User, UserDto> {
    @Override
    public User mapToDomain(UserDto restObject){
        if(restObject == null) return null;

        User user = new User();
        user.setName(restObject.name());
        user.setEmail(restObject.email());
        user.setImageUrl("");
        return user;
    }

}
