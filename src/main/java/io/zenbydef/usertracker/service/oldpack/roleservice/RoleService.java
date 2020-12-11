package io.zenbydef.usertracker.service.oldpack.roleservice;

import io.zenbydef.usertracker.io.entities.oldpack.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleService {
    List<Role> getRoles();

    Role getOneRole(Long id);

    Role getRoleByName(String name);
}
