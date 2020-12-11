package io.zenbydef.usertracker.service;

import io.zenbydef.usertracker.io.entities.oldpack.Role;
import io.zenbydef.usertracker.io.shared.RoleDto;

import java.util.List;

public interface RoleDtoService {
    List<RoleDto> getRoles();

    RoleDto getDistinctRole(String s);
}
