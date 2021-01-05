package io.zenbydef.usertracker.ui.controllers;

import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.security.annotations.*;
import io.zenbydef.usertracker.service.roleservice.RoleService;
import io.zenbydef.usertracker.service.userservice.UserService;
import io.zenbydef.usertracker.ui.models.request.operations.UserDetailsRequestModel;
import io.zenbydef.usertracker.ui.models.response.RoleRest;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/users")
public class UserControllerRest {
    private final UserService userService;
    private final RoleService roleService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserControllerRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @UserCreatePermission
    @PostMapping(consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> createUser(@RequestBody UserDetailsRequestModel requestModel) {
        UserDto convertedUser = modelMapper.map(requestModel, UserDto.class);
        UserDto createdUser = userService.createUser(convertedUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @UserListReadPermission
    @GetMapping(produces = "application/json")
    public List<UserRest> getAllUsers() {
        List<UserDto> userDtoList = userService.findUsers();
        return userDtoList.stream()
                .map(userDto -> modelMapper.map(userDto, UserRest.class))
                .collect(Collectors.toList());
    }

    @UserReadPermission
    @GetMapping(path = "/{userId}",
            produces = "application/json")
    public UserRest getUser(@PathVariable String userId) {
        UserDto userDto = userService.findUserByUserId(userId);
        return modelMapper.map(userDto, UserRest.class);
    }

    @UserListReadPermission
    @GetMapping(path = "/roles",
            produces = "application/json")
    public List<RoleRest> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getRoles();
        return roleDtoList.stream()
                .map(roleDto -> modelMapper.map(roleDto, RoleRest.class))
                .collect(Collectors.toList());
    }

    @UserViewProfilePermission
    @GetMapping(path = "/principal",
            produces = "application/json")
    public UserRest AuthenticateUser(Principal principal) {
        UserDto userDto = userService.findUserByName(principal.getName());
        return modelMapper.map(userDto, UserRest.class);
    }

    @UserUpdatePermission
    @PatchMapping(path = "/{userId}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable String userId,
                                        @RequestBody UserDetailsRequestModel userDetails) {
        UserDto convertedUser = modelMapper.map(userDetails, UserDto.class);
        UserDto userForUpdate = userService.updateUser(userId, convertedUser);
        return ResponseEntity.accepted().build();
    }

    @UserDeletePermission
    @DeleteMapping(path = "/{userId}",
            produces = "application/json")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}