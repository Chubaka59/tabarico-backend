package com.gtarp.tabaricobackend.exception;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException(String username) {
        super(username + " : the password is empty");
    }
}
