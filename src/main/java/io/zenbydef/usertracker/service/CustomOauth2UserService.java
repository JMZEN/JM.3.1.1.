package io.zenbydef.usertracker.service;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.repositories.RoleDtoRepository;
import io.zenbydef.usertracker.io.repositories.UserDtoRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.util.IdGenerator;
import io.zenbydef.usertracker.util.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    private final UserDtoRepository userDtoRepository;
    private final RoleDtoRepository roleDtoRepository;
    private final RoleManager roleManager;
    private IdGenerator idGenerator;
//    private PasswordEncoder encoder;

    public CustomOauth2UserService(UserDtoRepository userDtoRepository,
                                   RoleDtoRepository roleDtoRepository,
                                   RoleManager roleManager) {
        this.userDtoRepository = userDtoRepository;
        this.roleDtoRepository = roleDtoRepository;
        this.roleManager = roleManager;
    }

    @Autowired
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

//    @Autowired
//    public void setEncoder(PasswordEncoder encoder) {
//        this.encoder = encoder;
//    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        processOAuth2User(user.getAttributes());
        return user;
    }


    private void processOAuth2User(Map<String, Object> userAttributes) {
        Map<String, String> userAttributesMap =
                getUserAttributesAsStringMap(userAttributes);

        UserEntity foundUser =
                userDtoRepository.findUserByEmail(userAttributesMap.get("email"));
        if (foundUser != null) {
            updateExistingUser(userAttributesMap);
        } else {
            registerNewUser(userAttributesMap);
        }
    }


    private Map<String, String> getUserAttributesAsStringMap(Map<String, Object> userAttributes) {
        Map<String, String> userAttributesMap = new HashMap<>();
        userAttributesMap.put("email", (String) userAttributes.get("email"));
        userAttributesMap.put("firstName", (String) userAttributes.get("given_name"));
        userAttributesMap.put("lastName", (String) userAttributes.get("family_name"));
        return userAttributesMap;
    }

    private void updateExistingUser(Map<String, String> attributes) {
    }

    private void registerNewUser(Map<String, String> userAttributesMap) {
        RoleEntity roleEntity1 = roleDtoRepository.findAll().get(0);
        List<RoleEntity> roleEntityList = List.of(roleEntity1);

        UserEntity userForCreation = new UserEntity();
        userForCreation.setUserId(idGenerator.generateUserId(6));
        userForCreation.setEmail(userAttributesMap.get("email"));
        userForCreation.setEncryptedPassword("");
        userForCreation.setFirstName(userAttributesMap.get("firstName"));
        userForCreation.setLastName(userAttributesMap.get("lastName"));
        userForCreation.setAge(0);
        userForCreation.setRoles(roleEntityList);

        userDtoRepository.save(userForCreation);
    }
}
