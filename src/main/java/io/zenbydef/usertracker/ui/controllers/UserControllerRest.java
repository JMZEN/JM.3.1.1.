package io.zenbydef.usertracker.ui.controllers;

import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.security.annotations.*;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoService;
import io.zenbydef.usertracker.ui.models.request.operstions.UserDetailsRequestModel;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
public class UserControllerRest {
    private final UserDtoService userDtoService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserControllerRest(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @UserCreatePermission
    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public UserRest createUser(@RequestBody UserDetailsRequestModel requestModel) {
        UserDto userDto = modelMapper.map(requestModel, UserDto.class);
        UserDto createdUser = userDtoService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    //    @UserListReadPermission
    @GetMapping(produces = "application/json")
    public List<UserRest> getAllUsers() {
        List<UserDto> userDtoList = userDtoService.findUsers();
        return userDtoList.stream()
                .map(userDto -> modelMapper.map(userDto, UserRest.class))
                .collect(Collectors.toList());
    }

    @UserReadPermission
    @GetMapping(path = "/{userId}",
            produces = "application/json")
    public UserRest getUser(@PathVariable String userId) {
        UserDto userDto = userDtoService.findUserByUserId(userId);
        return modelMapper.map(userDto, UserRest.class);
    }

    @UserUpdatePermission
    @PutMapping(path = "/{userId}",
            consumes = "application/json",
            produces = "application/json")
    public UserRest updateUser(@PathVariable String userId,
                               @RequestBody UserDetailsRequestModel userDetails) {
        UserDto convertedUser = modelMapper.map(userDetails, UserDto.class);
        UserDto userForUpdate = userDtoService.updateUser(userId, convertedUser);
        return modelMapper.map(userForUpdate, UserRest.class);
    }

    @UserDeletePermission
    @DeleteMapping(path = "/{userId}",
            produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userDtoService.deleteUser(userId);
        return new ResponseEntity<>(String.format("User with %s", userId), HttpStatus.OK);
    }
}
