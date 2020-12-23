package io.zenbydef.usertracker.service.userdtoservice;

import io.zenbydef.usertracker.io.entities.UserEntity;
import io.zenbydef.usertracker.io.repositories.UserDtoRepository;
import io.zenbydef.usertracker.io.shared.UserDto;
import io.zenbydef.usertracker.util.IdGenerator;
import io.zenbydef.usertracker.util.RoleManager;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDtoServiceImpl implements UserDtoService {
    private final UserDtoRepository userDtoRepository;
    private final RoleManager roleManager;
    private final IdGenerator idGenerator;
    private final PasswordEncoder encoder;
    private static final ModelMapper modelMapper = new ModelMapper();

    public UserDtoServiceImpl(UserDtoRepository userDtoRepository,
                              RoleManager roleManager,
                              IdGenerator idGenerator,
                              PasswordEncoder encoder) {
        this.userDtoRepository = userDtoRepository;
        this.roleManager = roleManager;
        this.idGenerator = idGenerator;
        this.encoder = encoder;
    }

    @Override
    public UserDto createUser(UserDto user) {
        if (userDtoRepository.findUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntityForSave = modelMapper.map(user, UserEntity.class);
        userEntityForSave.setUserId(idGenerator.generateUserId(6));
        userEntityForSave.setEncryptedPassword(encoder.encode(user.getPassword()));
        userEntityForSave.setRoles(roleManager.convertRoleDtoToRoleEntity(user.getRoles()));

        UserEntity userEntitySaved = userDtoRepository.save(userEntityForSave);
        return modelMapper.map(userEntitySaved, UserDto.class);
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
        checkForRolesUpdate(userDto, foundUserEntityForUpdate);
        UserEntity savedUser = userDtoRepository.save(foundUserEntityForUpdate);
        return modelMapper.map(savedUser, UserDto.class);
    }

    private void checkForRolesUpdate(UserDto userDto, UserEntity foundUserEntityForUpdate) {
        if (userDto.getRoles().get(0).getNameOfRole() != null) {
            foundUserEntityForUpdate.setRoles(roleManager.convertRoleDtoToRoleEntity(userDto.getRoles()));
        }
    }

    private void createNewEncryptedPassword(UserDto userDto, UserEntity foundUserEntityForUpdate) {
        if (userDto.getPassword().length() != 0) {
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