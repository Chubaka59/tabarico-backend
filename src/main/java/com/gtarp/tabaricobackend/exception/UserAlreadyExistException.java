package com.gtarp.tabaricobackend.exception;

public class UserAlreadyExistException extends ConflictException {
    public UserAlreadyExistException(String username) {
        super("User with username " + username + " already exists");
    }
}
