package com.gtarp.tabaricobackend.exception;

public class ConsumableAlreadyExistException extends ConflictException {
    public ConsumableAlreadyExistException(String name) {
        super("Consumable with the name " + name + " already exists");
    }
}
