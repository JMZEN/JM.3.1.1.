package io.zenbydef.usertracker.service.oldpack.roleservice;

import io.zenbydef.usertracker.io.entities.oldpack.Role;
import io.zenbydef.usertracker.io.repositories.oldpack.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getOneRole(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByNameOfRole(name);
    }
}
