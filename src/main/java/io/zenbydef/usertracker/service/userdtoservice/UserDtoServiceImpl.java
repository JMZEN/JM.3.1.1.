package io.zenbydef.usertracker.service.userdtoservice;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.repositories.UserDtoRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.service.roledtoservice.RoleDtoService;
import io.zenbydef.usertracker.util.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {
    private final UserDtoRepository userDtoRepository;
    private final RoleDtoService roleDtoService;
    private final IdGenerator idGenerator;
    private final PasswordEncoder encoder;
    private List<RoleDto> roleDtos;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDtoServiceImpl(UserDtoRepository userDtoRepository,
                              RoleDtoService roleDtoService,
                              IdGenerator idGenerator,
                              PasswordEncoder encoder) {
        this.userDtoRepository = userDtoRepository;
        this.roleDtoService = roleDtoService;
        this.idGenerator = idGenerator;
        this.encoder = encoder;
    }

    @Autowired
    private void setRolesSet() {
        this.roleDtos = roleDtoService.getRoles();
    }

    @Override
    public UserDto createUser(UserDto user) {
        if (userDtoRepository.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntityForSave = modelMapper.map(user, UserEntity.class);
        userEntityForSave.setUserId(idGenerator.generateUserId(6));
        userEntityForSave.setEncryptedPassword(encoder.encode(user.getPassword()));
        userEntityForSave.setRoles(convertRoleDtoToRoleEntity(user.getRoles()));

        UserEntity userEntitySaved = userDtoRepository.save(userEntityForSave);
        return modelMapper.map(userEntitySaved, UserDto.class);
    }

    private List<RoleEntity> convertRoleDtoToRoleEntity(Collection<RoleDto> roles) {
        List<RoleDto> distinctRoles = getRoleDistinctRolesForUserDto(roles);
        return distinctRoles.stream()
                .map(roleDto -> modelMapper.map(roleDto, RoleEntity.class))
                .collect(Collectors.toList());
    }

    private List<RoleDto> getRoleDistinctRolesForUserDto(Collection<RoleDto> roles) {
        return roles.stream()
                .map(roleDto -> getDistinctRole(roleDto.getNameOfRole()))
                .collect(Collectors.toList());
    }

    private RoleDto getDistinctRole(String s) {
        RoleDto roleToFind = null;
        for (RoleDto role : roleDtos) {
            if (s.equalsIgnoreCase(role.getNameOfRole())) {
                roleToFind = role;
            }
        }
        return Objects.requireNonNull(roleToFind);
    }

    @Override
    public UserDto findUserByName(String userName) {
        UserEntity foundUserEntity = userDtoRepository.findUserByEmail(userName);
        if (foundUserEntity == null) {
            throw new NullPointerException(String.format("User with username %s not found", userName));
        }
        return modelMapper.map(foundUserEntity, UserDto.class);
    }

    @Override
    public UserDto findUserByUserId(String userId) {
        UserEntity foundUserEntity = userDtoRepository.findUserByUserId(userId);
        if (foundUserEntity == null) {
            throw new NullPointerException(String.format("User with userId %s not found", userId));
        }
        return modelMapper.map(foundUserEntity, UserDto.class);
    }

    @Override
    public List<UserDto> findUsers() {
        List<UserEntity> foundUserEntities = userDtoRepository.findAll();
        return foundUserEntities.stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity foundUserEntityForUpdate = userDtoRepository.findUserByUserId(userId);
        uniqueUserCheck(foundUserEntityForUpdate);
        createNewEncryptedPassword(userDto, foundUserEntityForUpdate);
        foundUserEntityForUpdate.setFirstName(userDto.getFirstName());
        foundUserEntityForUpdate.setLastName(userDto.getLastName());
        foundUserEntityForUpdate.setAge(userDto.getAge());
        foundUserEntityForUpdate.setRoles(convertRoleDtoToRoleEntity(userDto.getRoles()));
        UserEntity savedUser = userDtoRepository.save(foundUserEntityForUpdate);
        return modelMapper.map(savedUser, UserDto.class);
    }

    private void createNewEncryptedPassword(UserDto userDto, UserEntity foundUserEntityForUpdate) {
        if (userDto.getPassword() != null) {
            foundUserEntityForUpdate.setEncryptedPassword(encoder.encode(userDto.getPassword()));
        }
    }

    private void uniqueUserCheck(UserEntity foundUserEntityForUpdate) {
        if (foundUserEntityForUpdate == null) {
            throw new RuntimeException("User is not found");
        }
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity foundUserEntity = userDtoRepository.findUserByUserId(userId);
        if (foundUserEntity == null) {
            throw new RuntimeException(String.format("User with id %s not found for deletion", userId));
        }
        userDtoRepository.delete(foundUserEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDto foundUserDto = findUserByName(userName);
        return new User(userName,
                foundUserDto.getEncryptedPassword(),
                foundUserDto.getAuthorities());
    }
}
