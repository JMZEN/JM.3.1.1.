package io.zenbydef.usertracker;

import io.zenbydef.usertracker.io.entities.Privilege;
import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.repositories.UserDtoRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.service.RoleDtoService;
import io.zenbydef.usertracker.service.userdtoservice.UserDtoServiceImpl;
import io.zenbydef.usertracker.util.IdGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserDtoServiceImpl must")
public class UserDtoServiceImplTest {
    @InjectMocks
    UserDtoServiceImpl userDtoService;

    @Mock
    UserDtoRepository userDtoRepository;

    @Mock
    IdGenerator idGenerator;

    @Mock
    PasswordEncoder encoder;

    private final List<UserEntity> USERS = getUsers();
    private final UserEntity USER_ENTITY = getUsers().get(0);
    private final String USERNAME = "user1@test.com";
    private final Long USER_ID_LONG = 1L;
    private final String USER_ID_STRING = "BK5L5L";
    private final String ENCRYPTED_PASSWORD = "sdfsdfs212asd";

    @Test
    @DisplayName("create User")
    final void testCreateUser() {
        when(userDtoRepository.findUserByEmail(anyString())).thenReturn(null);
        when(idGenerator.generateUserId(anyInt())).thenReturn(USER_ID_STRING);
        when(encoder.encode(null)).thenReturn(ENCRYPTED_PASSWORD);
        when(userDtoRepository.save(any(UserEntity.class))).thenReturn(USER_ENTITY);

        UserDto userDto = userDtoService.createUser(getDtoUsers().get(0));
        assertNotNull(userDto);
    }

    @Test
    @DisplayName("throw exc if user already exists")
    final void testCreateUser_NullPointerException() {
        when(userDtoRepository.findUserByEmail(anyString())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userDtoService.createUser(getDtoUsers().get(0)));
    }

    @Test
    @DisplayName("find User by username")
    final void testFindUserByName_basic() {
        when(userDtoRepository.findUserByEmail(anyString())).thenReturn(USER_ENTITY);
        UserDto foundUserDto = userDtoService.findUserByName(USERNAME);

        assertNotNull(foundUserDto);
        assertEquals("user1@test.com", foundUserDto.getEmail());
    }

    @Test
    @DisplayName("throw exception if User not found")
    final void testFindUserByName_NullPointerException() {
        when(userDtoRepository.findUserByEmail(anyString())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userDtoService.findUserByName(USERNAME));
    }

    @Test
    @DisplayName("finds user by userId")
    final void testFindUserByUserId_basic() {
        when(userDtoRepository.findUserByUserId(anyString())).thenReturn(USER_ENTITY);
        UserDto userDto = userDtoService.findUserByUserId(USER_ID_STRING);

        assertNotNull(userDto);
        assertEquals("BK5L5L", userDto.getUserId());
        assertEquals("user1@test.com", userDto.getEmail());
    }

    @Test
    @DisplayName("throw the exception if User not found by userId")
    final void testFindUserById_NullPointerException() {
        when(userDtoRepository.findUserByUserId(anyString())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userDtoService.findUserByUserId(USER_ID_STRING));
    }

    @Test
    @DisplayName("get all Users")
    final void testFindUsers_basic() {
        when(userDtoRepository.findAll()).thenReturn(USERS);

        List<UserDto> usersFound = userDtoService.findUsers();
        assertNotNull(usersFound);
        assertEquals(USERS.size(), usersFound.size());
        assertEquals("BK5L5L", usersFound.get(0).getUserId());
        assertEquals("user1@test.com", usersFound.get(0).getEmail());
    }

    @Test
    @DisplayName("delete User")
    final void testDeleteUser_basic() {
        when(userDtoRepository.findUserByUserId(anyString())).thenReturn(USER_ENTITY);
        userDtoService.deleteUser(USER_ID_STRING);
        verify(userDtoRepository).delete(USER_ENTITY);
    }

    @Test
    @DisplayName("throw the exception if User not found for deletion")
    final void testDeleteUser_NullPointerException() {
        when(userDtoRepository.findUserByUserId(anyString())).thenReturn(null);
        assertThrows(NullPointerException.class, () -> userDtoService.findUserByUserId(USER_ID_STRING));
    }

    private List<UserEntity> getUsers() {
        UserEntity user1 = new UserEntity();
        user1.setId(USER_ID_LONG);
        user1.setUserId(USER_ID_STRING);
        user1.setEmail("user1@test.com");
        user1.setEncryptedPassword(ENCRYPTED_PASSWORD);
        user1.setFirstName("Hello");
        user1.setLastName("World");
        user1.setAge(55);
        user1.setRoles(getRoleForUser1());
        return List.of(user1);
    }

    private List<RoleEntity> getRoleForUser1() {
        RoleEntity role1 = new RoleEntity();
        role1.setId(2L);
        role1.setNameOfRole("USER");
        role1.setPrivileges(getPrivilegeForUser1());
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
        role1.setPrivileges(getPrivilegeForUser1());
        return List.of(role1);
    }

    private List<Privilege> getPrivilegeForUser1() {
        Privilege viewPrivilege = new Privilege("user.view.profile");
        return List.of(viewPrivilege);
    }
}
