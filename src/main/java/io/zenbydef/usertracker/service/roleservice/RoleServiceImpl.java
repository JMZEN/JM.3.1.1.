package io.zenbydef.usertracker.service.roleservice;

import io.zenbydef.usertracker.io.entities.RoleEntity;
import io.zenbydef.usertracker.io.repositories.RoleDtoRepository;
import io.zenbydef.usertracker.io.shared.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDtoRepository roleDtoRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    public RoleServiceImpl(RoleDtoRepository roleDtoRepository) {
        this.roleDtoRepository = roleDtoRepository;
    }

    @Override
    public List<RoleDto> getRoles() {
        List<RoleEntity> roleEntities = roleDtoRepository.findAll();
        return roleEntities.stream()
                .map(roleEntity -> modelMapper.map(roleEntity, RoleDto.class))
                .collect(Collectors.toList());
    }
}
