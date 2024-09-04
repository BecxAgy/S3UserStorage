package io.github.becxagy.s3_file.adapter.entrypoint.user;

import io.github.becxagy.s3_file.adapter.entrypoint.model.UserDto;
import io.github.becxagy.s3_file.application.port.entrypoint.user.ChangeUserEndepointPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private ChangeUserEndepointPort changeUserEndepointPort;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@ModelAttribute UserDto userDto){
        changeUserEndepointPort.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
