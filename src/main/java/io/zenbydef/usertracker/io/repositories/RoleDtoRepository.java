package io.zenbydef.usertracker.io.repositories;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.entities.oldpack.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDtoRepository extends JpaRepository<RoleEntity, Long> {
    Role findByNameOfRole(String roleName);
}