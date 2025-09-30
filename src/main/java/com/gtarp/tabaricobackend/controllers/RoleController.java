package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.RoleDto;
import com.gtarp.tabaricobackend.entities.Role;
import com.gtarp.tabaricobackend.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class RoleController {
    @Autowired
    private CrudService<Role, RoleDto> roleService;

    @GetMapping("/roles")
    public List<Role> getRoleList() {
        return roleService.getAll();
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Integer id) {
        try {
            Role role = roleService.getById(id);
            return new ResponseEntity<>(role, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du role avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> insert(@RequestBody @Validated RoleDto roleDto) {
        try {
            roleService.insert(roleDto);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error("Erreur lors de la création du role", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> update(@RequestBody @Validated RoleDto roleDto, @PathVariable Integer id) {
        try {
            roleService.update(id, roleDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du role avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
