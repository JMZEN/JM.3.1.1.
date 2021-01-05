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
    public ResponseEntity<List<UserRest>> getAllUsers() {
        List<UserDto> userDtoList = userService.findUsers();
        List<UserRest> foundUsers = userDtoList.stream()
                .map(userDto -> modelMapper.map(userDto, UserRest.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    @UserReadPermission
    @GetMapping(path = "/{userId}",
            produces = "application/json")
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        UserDto userDto = userService.findUserByUserId(userId);
        UserRest foundUser = modelMapper.map(userDto, UserRest.class);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @UserListReadPermission
    @GetMapping(path = "/roles",
            produces = "application/json")
    public ResponseEntity<List<RoleRest>> getAllRoles() {
        List<RoleDto> roleDtoList = roleService.getRoles();
        List<RoleRest> foundRoles = roleDtoList.stream()
                .map(roleDto -> modelMapper.map(roleDto, RoleRest.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(foundRoles, HttpStatus.OK);
    }

    @UserViewProfilePermission
    @GetMapping(path = "/principal",
            produces = "application/json")
    public ResponseEntity<UserRest> AuthenticateUser(Principal principal) {
        UserDto userDto = userService.findUserByName(principal.getName());
        UserRest foundUser = modelMapper.map(userDto, UserRest.class);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
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