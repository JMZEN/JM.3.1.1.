package io.zenbydef.usertracker.util;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.shared.RoleDto;
import io.zenbydef.usertracker.service.roleservice.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RoleManager {
    private List<RoleDto> roleDtos;
    private final RoleService roleService;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RoleManager(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    private void setRolesSet() {
        this.roleDtos = roleService.getRoles();
    }

    public List<RoleEntity> convertRoleDtoToRoleEntity(Collection<RoleDto> roles) {
        List<RoleDto> distinctRoles = getRoleDistinctRolesForUserDto(roles);
        return distinctRoles.stream()
                .map(roleDto -> modelMapper.map(roleDto, RoleEntity.class))
                .collect(Collectors.toList());
    }

    private List<RoleDto> getRoleDistinctRolesForUserDto(Collection<RoleDto> roles) {
        List<RoleDto> list = new ArrayList<>();
        for (RoleDto roleDto : roles) {
            RoleDto distinctRole = getDistinctRole(roleDto.getNameOfRole());
            if (distinctRole.getNameOfRole() != null) {
                list.add(distinctRole);
            }
        }
        return list;
    }

    private RoleDto getDistinctRole(String s) {
        return roleDtos.stream()
                .filter(roleDto -> roleDto.getNameOfRole().equalsIgnoreCase(s))
                .findFirst()
                .orElse(new RoleDto());
    }
}

//        for (RoleDto role : roleDtos) {
//            if (s.equalsIgnoreCase(role.getNameOfRole())) {
//                roleToFind = role;
//            }
//        }