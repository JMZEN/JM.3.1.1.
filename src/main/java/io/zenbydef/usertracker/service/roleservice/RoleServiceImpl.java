package io.zenbydef.usertracker.service.roleservice;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.repositories.RoleRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> getRoles() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        return roleEntities.stream()
                .map(roleEntity -> modelMapper.map(roleEntity, RoleDto.class))
                .collect(Collectors.toList());
    }
}
