package io.zenbydef.usertracker.ui.controllers;

import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.security.annotations.*;
import io.zenbydef.usertracker.service.roledtoservice.RoleDtoService;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoService;
import io.zenbydef.usertracker.ui.models.request.operations.UserDetailsRequestModel;
import io.zenbydef.usertracker.ui.models.response.RoleRest;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
public class UserControllerRest {
    private final UserDtoService userDtoService;
    private final RoleDtoService roleDtoService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserControllerRest(UserDtoService userDtoService, RoleDtoService roleDtoService) {
        this.userDtoService = userDtoService;
        this.roleDtoService = roleDtoService;
    }

    @UserCreatePermission
    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public UserRest createUser(@RequestBody UserDetailsRequestModel requestModel) {
        UserDto convertedUser = modelMapper.map(requestModel, UserDto.class);
        UserDto createdUser = userDtoService.createUser(convertedUser);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @UserListReadPermission
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

    @UserListReadPermission
    @GetMapping(path = "/roles",
            produces = "application/json")
    public List<RoleRest> getAllRoles() {
        List<RoleDto> roleDtoList = roleDtoService.getRoles();
        return roleDtoList.stream()
                .map(roleDto -> modelMapper.map(roleDto, RoleRest.class))
                .collect(Collectors.toList());
    }

    @UserViewProfilePermission
    @GetMapping(path = "/principal",
            produces = "application/json")
    public UserRest AuthenticateUser(@AuthenticationPrincipal User principal) {
        UserDto userDto = userDtoService.findUserByName(principal.getUsername());
        return modelMapper.map(userDto, UserRest.class);
    }

    @UserUpdatePermission
    @PutMapping(path = "/{userId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable String userId,
                                        @RequestBody UserDetailsRequestModel userDetails) {
        UserDto convertedUser = modelMapper.map(userDetails, UserDto.class);
        UserDto userForUpdate = userDtoService.updateUser(userId, convertedUser);
        return ResponseEntity.accepted().build();
    }

    @UserDeletePermission
    @DeleteMapping(path = "/{userId}",
            produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userDtoService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}