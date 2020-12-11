package io.zenbydef.usertracker.service;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.oldpack.Role;
import io.zenbydef.usertracker.io.repositories.RoleDtoRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.io.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RoleDtoServiceImpl implements RoleDtoService {
    private final RoleDtoRepository roleDtoRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RoleDtoServiceImpl(RoleDtoRepository roleDtoRepository) {
        this.roleDtoRepository = roleDtoRepository;
    }

    @Override
    public List<RoleDto> getRoles() {
        List<RoleEntity> roleEntities = roleDtoRepository.findAll();
        return roleEntities.stream()
                .map(roleEntity -> modelMapper.map(roleEntity, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto getDistinctRole(String s) {
        RoleDto roleToFind = null;
        for (RoleDto role : getRoles()) {
            if (s.equalsIgnoreCase(role.getNameOfRole())) {
                roleToFind = role;
            }
        }
        return Objects.requireNonNull(roleToFind);
    }
}
