package io.zenbydef.usertracker.service.customoauth2userservice;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.repositories.RoleRepository;
import io.zenbydef.usertracker.io.repositories.UserRepository;
import io.zenbydef.usertracker.security.models.CustomOAuth2User;
import io.zenbydef.usertracker.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private IdGenerator idGenerator;

    public CustomOauth2UserService(UserRepository userRepository,
                                   RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        UserEntity userEntity = processOAuth2User(user.getAttributes());

        CustomOAuth2User customOAuth2User = createCustomOAuth2User(user, userEntity);
        return customOAuth2User;
    }

    private CustomOAuth2User createCustomOAuth2User(OAuth2User user, UserEntity userEntity) {
        CustomOAuth2User customOAuth2User = new CustomOAuth2User();
        customOAuth2User.setName(userEntity.getEmail());
        customOAuth2User.setAuthorities(userEntity.getAuthorities());
        customOAuth2User.setAttributes(user.getAttributes());
        return customOAuth2User;
    }

    private UserEntity processOAuth2User(Map<String, Object> userAttributes) {
        Map<String, String> userAttributesMap =
                getUserAttributesAsStringMap(userAttributes);

        UserEntity foundUser =
                userRepository.findUserByEmail(userAttributesMap.get("email"));
        if (foundUser == null) {
            return registerNewUser(userAttributesMap);
        }

        return foundUser;
    }

    private Map<String, String> getUserAttributesAsStringMap(Map<String, Object> userAttributes) {
        Map<String, String> userAttributesMap = new HashMap<>();
        userAttributesMap.put("email", (String) userAttributes.get("email"));
        userAttributesMap.put("firstName", (String) userAttributes.get("given_name"));
        userAttributesMap.put("lastName", (String) userAttributes.get("family_name"));
        return userAttributesMap;
    }

    private UserEntity registerNewUser(Map<String, String> userAttributesMap) {
        RoleEntity userRoleEntity = roleRepository.findAll().get(0);
        List<RoleEntity> roleEntityList = List.of(userRoleEntity);

        UserEntity userForCreation = setNewUserEntity(userAttributesMap, roleEntityList);
        return userRepository.save(userForCreation);
    }

    private UserEntity setNewUserEntity(Map<String, String> userAttributesMap, List<RoleEntity> roleEntityList) {
        UserEntity userForCreation = new UserEntity();
        userForCreation.setUserId(idGenerator.generateUserId(6));
        userForCreation.setEmail(userAttributesMap.get("email"));
        userForCreation.setEncryptedPassword("");
        userForCreation.setFirstName(userAttributesMap.get("firstName"));
        userForCreation.setLastName(userAttributesMap.get("lastName"));
        userForCreation.setAge(0);
        userForCreation.setRoles(roleEntityList);
        return userForCreation;
    }
}