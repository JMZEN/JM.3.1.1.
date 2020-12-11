//package io.zenbydef.usertracker;
//
//import io.zenbydef.usertracker.io.entities.oldpack.Role;
//import io.zenbydef.usertracker.io.entities.UserEntity;
//import io.zenbydef.usertracker.io.repositories.old.UserRepository;
//import io.zenbydef.usertracker.service.userservice.UserServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("UserServiceImpl")
//public class UserServiceImplTest {
//
//    @InjectMocks
//    UserServiceImpl userService;
//
//    @Mock
//    UserRepository userRepository;
//
//    private final UserEntity user = getUser();
//    private final List<UserEntity> users = getUsers();
//    private final List<Role> rolesForUser1 = getRoleUser1();
//    private final List<Role> rolesForUser2 = getRoleUser2();
//
//    @Test
//    @DisplayName("must get User by UserId")
//    final void testGetUserById() {
//        when(userRepository.findUserById(anyLong())).thenReturn(user);
//
//        UserEntity foundUser = userService.getUserById(1L);
//
//        assertNotNull(foundUser);
//        assertEquals(1L, foundUser.getId());
//    }
//
//    @Test
//    @DisplayName("must get all Users")
//    final void testGetAllUsers() {
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<UserEntity> usersFound = userService.getUsers();
//
//        assertEquals(users.size(), usersFound.size());
//    }
//
//    private UserEntity getUser() {
//        UserEntity user = new UserEntity();
//        user.setId(1L);
//        user.setUsername("user1@test.com");
//        user.setPassword("pass");
//        user.setRoles(getRoleUser1());
//        return user;
//    }
//
//    private List<UserEntity> getUsers() {
//        UserEntity user1 = new UserEntity();
//        user1.setId(1L);
//        user1.setUsername("user1@test.com");
//        user1.setPassword("pass");
//        user1.setRoles(rolesForUser1);
//
//        UserEntity user2 = new UserEntity();
//        user2.setId(2L);
//        user2.setUsername("user2@test.com");
//        user2.setPassword("pass");
//        user2.setRoles(rolesForUser2);
//
//        return List.of(user1, user2);
//    }
//
//    private List<Role> getRoleUser1() {
//        Role role1 = new Role(2L, "USER");
//        return List.of(role1);
//    }
//
//    private List<Role> getRoleUser2() {
//        Role role1 = new Role(1L, "ADMIN");
//        Role role2 = new Role(2L, "USER");
//        return List.of(role1, role2);
//    }
//}