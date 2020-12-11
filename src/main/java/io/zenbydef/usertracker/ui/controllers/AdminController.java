package io.zenbydef.usertracker.ui.controllers;

import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.security.annotations.*;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoService;
import io.zenbydef.usertracker.service.oldpack.roleservice.userservice.UserService;
import io.zenbydef.usertracker.ui.models.request.UserDetailsRequestModel;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import io.zenbydef.usertracker.util.RoleManager;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserDtoService userDtoService;
    private final RoleManager roleManager;
    private final PasswordEncoder passwordEncoder;
    private static final ModelMapper modelMapper = new ModelMapper();

    public AdminController(UserService userService, UserDtoService userDtoService, RoleManager roleManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userDtoService = userDtoService;
        this.roleManager = roleManager;
        this.passwordEncoder = passwordEncoder;
    }

    @UserCreatePermission
    @PutMapping(name = "/list", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userDtoService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @UserListReadPermission
    @GetMapping(name = "/list",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getAllUsers() {
        List<UserDto> userDtoList = userDtoService.findUsers();
        return userDtoList.stream()
                .map(userDto -> modelMapper.map(userDto, UserRest.class))
                .collect(Collectors.toList());
    }

    @UserDeletePermission
    @DeleteMapping(name = "/list/{userId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userDtoService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @UserListReadPermission
//    @GetMapping("/list")
//    public ModelAndView listUsers() {
//        List<User> userList = userService.getUsers();
//        return new ModelAndView("pages/admindirectory/users-table", "usersForTable", userList);
//    }

//    @UserViewProfilePermission
//    @GetMapping("/user")
//    public ModelAndView getUserProfile(@RequestParam("userId") Long userId) {
//        UserEntity detailUser = userService.getUserById(userId);
////        Collection<String> roles = detailUser.getRolesAsStrings();
//        ModelAndView modelAndView = new ModelAndView("pages/userdirectory/user-page");
//        modelAndView.addObject("user", detailUser);
//        modelAndView.addObject("userRoles", roles);
//        return modelAndView;
//    }

//    @UserCreatePermission
//    @GetMapping("/add")
//    public ModelAndView addUser() {
//        User detailUser = new User();
//        ModelAndView modelAndView = new ModelAndView("pages/admindirectory/user-form");
//        modelAndView.addObject("user", detailUser);
//        modelAndView.addObject("allRoles", roleManager.getStringRoles());
//        return modelAndView;
//    }
//
//    @UserCreatePermission
//    @PostMapping("/add")
//    public String saveUser(@ModelAttribute("user") User user,
//                           @RequestParam("roles") String[] roles) {
//        user.setRoles(roleManager.convertRoles(roles));
//        user.setPassword(passwordEncoder.encode((user.getPassword())));
//        userService.saveUser(user);
//        return "redirect:/admin/list";
//    }

//    @UserUpdatePermission
//    @GetMapping("/update")
//    public ModelAndView showFormForUpdate(@RequestParam("userId") Long userId) {
//        UserEntity detailUser = userService.getUserById(userId);
//        ModelAndView modelAndView = new ModelAndView("pages/admindirectory/user-update");
//        modelAndView.addObject("userId", userId);
////        modelAndView.addObject("username", detailUser.getUsername());
//        modelAndView.addObject("allRoles", roleManager.getStringRoles());
//        return modelAndView;
//    }
//
//    @UserCreatePermission
//    @PostMapping("/update")
//    public String saveUpdatedUser(@RequestParam("userId") Long userId,
//                                  @RequestParam("username") String username,
//                                  @RequestParam("roles") String[] roles) {
//        UserEntity detailUser = userService.getUserById(userId);
////        detailUser.setUsername(username);
//        detailUser.setRoles(roleManager.convertRoles(roles));
//        userService.saveUser(detailUser);
//        return "redirect:/admin/list";
//    }
//
//    @UserUpdatePermission
//    @GetMapping("/updatepass")
//    public ModelAndView updatePassword(@RequestParam("userId") Long userId) {
//        ModelAndView modelAndView = new ModelAndView("pages/admindirectory/user-change-pass");
//        modelAndView.addObject("userId", userId);
//        return modelAndView;
//    }
//
//    @UserUpdatePermission
//    @PostMapping("/updatepass")
//    public String savePassword(@RequestParam("userId") Long userId, @RequestParam("pass") String password) {
//        UserEntity detailUser = userService.getUserById(userId);
//        detailUser.setPassword(passwordEncoder.encode((password)));
//        userService.saveUser(detailUser);
//        return "redirect:/admin/list";
//    }
//
//    @UserDeletePermission
//    @PostMapping("/delete")
//    public ModelAndView deleteUser(@RequestParam("userId") Long userId) {
//        userService.deleteUser(userId);
//        return new ModelAndView("redirect:/admin/list");
//    }
}
