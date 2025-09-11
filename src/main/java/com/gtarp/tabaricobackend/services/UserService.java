package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.UserDto;
import com.gtarp.tabaricobackend.entities.User;

public interface UserService extends CrudService<User, UserDto> {
    /**
     * get a user by its username
     * @param username the username of the user
     * @return a user
     */
    User getByUsername(String username);

    /**
     * update the password of a user
     * @param username the username of the user
     * @param password the password to update
     */
    void updatePassword(String username, String password);

    /**
     * reset the accounting of the week
     */
    void resetWeeklyUserAccounting();
}
