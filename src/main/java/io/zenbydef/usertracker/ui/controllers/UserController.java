package io.zenbydef.usertracker.ui.controllers;

import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.security.annotations.UserViewProfilePermission;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoService;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class UserController {
    private final UserDtoService userDtoService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserController(UserDtoService userService) {
        this.userDtoService = userService;
    }

    @UserViewProfilePermission
    @GetMapping("/user")
    public UserRest indexPage(Principal principal) {
        UserDto foundUserDto = userDtoService.findUserByName(principal.getName());
        return modelMapper.map(foundUserDto, UserRest.class);
    }
}
