package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.RoleDto;
import com.gtarp.tabaricobackend.dto.UserDto;
import com.gtarp.tabaricobackend.entities.Role;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.services.CrudService;
import com.gtarp.tabaricobackend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CrudService<Role, RoleDto> roleService;

    @GetMapping("/users")
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de l'utilisateur avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> insert(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone,
            @RequestParam("role") Integer roleId,
            @RequestParam(value = "password", required = false) String password,
            @RequestPart(value = "identityCard", required = false) MultipartFile identityCardImage,
            @RequestParam("dateOfHire") LocalDate dateOfHire
    ) {
        try {
            UserDto userDto = new UserDto();
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setUsername(username);
            userDto.setPhone(phone);
            userDto.setRole(roleService.getById(roleId));
            userDto.setPassword(password);
            userDto.setIdentityCardImage(identityCardImage);
            userDto.setDateOfHire(dateOfHire);
            userService.insert(userDto);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'utilisateur", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'utilisateur avec l'id : {}", id ,e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> update(
            @PathVariable("id") Integer id,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone,
            @RequestParam("role") Integer roleId,
            @RequestParam(value = "password", required = false) String password,
            @RequestPart(value = "identityCard", required = false) MultipartFile identityCardImage,
            @RequestParam("dateOfHire") LocalDate dateOfHire
    ) {
        try {
            UserDto userDto = new UserDto();
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setUsername(username);
            userDto.setPhone(phone);
            userDto.setRole(roleService.getById(roleId));
            userDto.setPassword(password);
            userDto.setIdentityCardImage(identityCardImage);
            userDto.setDateOfHire(dateOfHire);
            userService.update(id, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour de l'utilisateur avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> body, Authentication authentication) {
        try {
            userService.updatePassword(authentication.getName(), body.get("newPassword"));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la modification du mot de passe pour l'utilisateur : {}", authentication.getName(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/reset-accounting")
    public ResponseEntity<?> resetAccounting() {
        try {
            userService.resetWeeklyUserAccounting();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la réinitialisation de la compta");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
