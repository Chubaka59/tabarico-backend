package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.RoleDto;
import com.gtarp.tabaricobackend.entities.Role;
import com.gtarp.tabaricobackend.exception.RoleAlreadyExistException;
import com.gtarp.tabaricobackend.exception.RoleNotFoundException;
import com.gtarp.tabaricobackend.repositories.RoleRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl extends AbstractCrudService<Role, RoleRepository, RoleDto> {

    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Role getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public Role insert(RoleDto roleDto) {
        Optional<Role> role = repository.findRoleByName(roleDto.getName());
        if (role.isPresent()) {
            throw new RoleAlreadyExistException(roleDto.getName());
        }
        Role newRole = new Role(roleDto);
        return this.repository.save(newRole);
    }
}
