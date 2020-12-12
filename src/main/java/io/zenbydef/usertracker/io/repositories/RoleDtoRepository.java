package io.zenbydef.usertracker.io.repositories;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDtoRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByNameOfRole(String roleName);
}