package io.zenbydef.usertracker.util;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.service.roledtoservice.RoleDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoleManager {
    private List<RoleDto> roleDtos;
    private final RoleDtoService roleDtoService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RoleManager(RoleDtoService roleDtoService) {
        this.roleDtoService = roleDtoService;
    }

    @Autowired
    private void setRolesSet() {
        this.roleDtos = roleDtoService.getRoles();
    }

    public List<RoleEntity> convertRoleDtoToRoleEntity(Collection<RoleDto> roles) {
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
}