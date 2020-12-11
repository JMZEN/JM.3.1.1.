package io.zenbydef.usertracker;

import io.zenbydef.usertracker.io.entities.Privilege;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoService;
import io.zenbydef.usertracker.ui.controllers.AdminController;
import io.zenbydef.usertracker.ui.models.request.RoleRequestModel;
import io.zenbydef.usertracker.ui.models.request.UserDetailsRequestModel;
import io.zenbydef.usertracker.ui.models.response.RoleRest;
import io.zenbydef.usertracker.ui.models.response.UserRest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdminController must")
public class AdminControllerTest {

    @InjectMocks
    AdminController adminController;

    @Mock
    UserDtoService userDtoService;

    private final List<UserRest> USERS = getUserRests();
    private final UserRest USER_REST = getUserRests().get(0);
    private final String EMAIL = "user1@test.com";
    private final Long USER_ID_LONG = 1L;
    private final String USER_ID_STRING = "BK5L5L";
    private final String ENCRYPTED_PASSWORD = "sdfsdfs212asd";

//    @Test
//    @DisplayName("create user")
//    final void testCreateUser() {
//        when(userDtoService.createUser(getDtoUsers().get(0))).thenReturn(getDtoUsers().get(0));
//        UserRest createdUser = adminController.createUser(model);
//
//        assertNotNull(createdUser);
//    }

    @Test
    @DisplayName("get all users")
    final void testGetAllUsers() {
        when(userDtoService.findUsers()).thenReturn(getDtoUsers());
        List<UserRest> foundUserRests = adminController.getAllUsers();

        assertNotNull(foundUserRests);
        assertEquals(getDtoUsers().size(), foundUserRests.size());
        assertEquals(getDtoUsers().get(0).getUserId(), foundUserRests.get(0).getUserId());
    }

    @Test
    @DisplayName("delete User")
    final void testDeleteUser() {
        adminController.deleteUser(USER_ID_STRING);
        verify(userDtoService, times(1)).deleteUser(USER_ID_STRING);
    }

    private List<UserRest> getUserRests() {
        UserRest user1 = new UserRest();
        user1.setUserId(USER_ID_STRING);
        user1.setEmail("user1@test.com");
        user1.setFirstName("Hello");
        user1.setLastName("World");
        user1.setAge(55);
        user1.setRoles(getRoleForUser1());
        return List.of(user1);
    }

    private List<RoleRest> getRoleForUser1() {
        RoleRest role1 = new RoleRest();
        role1.setId(2L);
        role1.setNameOfRole("USER");
        role1.setPrivileges(getPrivilege());
        return List.of(role1);
    }

    private List<UserDto> getDtoUsers() {
        UserDto user1 = new UserDto();
        user1.setId(USER_ID_LONG);
        user1.setUserId(USER_ID_STRING);
        user1.setEmail("user1@test.com");
        user1.setEncryptedPassword(ENCRYPTED_PASSWORD);
        user1.setFirstName("Hello");
        user1.setLastName("World");
        user1.setAge(55);
        user1.setRoles(getRoleForDtoUser1());
        return List.of(user1);
    }

    private List<RoleDto> getRoleForDtoUser1() {
        RoleDto role1 = new RoleDto();
        role1.setId(2L);
        role1.setNameOfRole("USER");
        role1.setPrivileges(getPrivilege());
        return List.of(role1);
    }

//    private UserDetailsRequestModel model = getUserDetailsRequestModel();
//    private RoleRequestModel roleRequestModel = getRoleRequestModel();
//
//    private UserDetailsRequestModel getUserDetailsRequestModel() {
//        UserDetailsRequestModel model = new UserDetailsRequestModel();
//        model.setEmail(EMAIL);
//        model.setFirstName("Hello");
//        model.setLastName("World");
//        model.setAge(55);
//        model.setRoles(List.of(roleRequestModel));
//        return model;
//    }
//
//    private RoleRequestModel getRoleRequestModel() {
//        RoleRequestModel model = new RoleRequestModel();
//        model.setNameOfRole("USER");
//        model.setUsers(Set.of(getUserDetailsRequestModel()));
//        model.setPrivileges(getPrivilege());
//        return model;
//    }
//
    private List<Privilege> getPrivilege() {
        Privilege viewPrivilege = new Privilege("user.view.profile");
        return List.of(viewPrivilege);
    }
}
