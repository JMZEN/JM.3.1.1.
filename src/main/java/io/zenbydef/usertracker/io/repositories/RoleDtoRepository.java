package io.zenbydef.usertracker.io.repositories;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDtoRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findRoleByNameOfRole(String nameOfRole);
}