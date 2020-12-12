package io.zenbydef.usertracker.service.userdtoservice;

import io.zenbydef.usertracker.io.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserDtoService extends UserDetailsService {
    UserDto createUser(UserDto user);

    UserDto findUserByName(String userName);

    UserDto findUserByUserId(String userId);

    List<UserDto> findUsers();

    UserDto updateUser(String userId, UserDto userDto);

    void deleteUser(String userId);
}
