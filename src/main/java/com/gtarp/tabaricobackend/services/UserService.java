package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.UserDto;
import com.gtarp.tabaricobackend.entities.User;

public interface UserService extends CrudService<User, UserDto> {
    User getByUsername(String username);
    void updatePassword(String username, String password);
}
