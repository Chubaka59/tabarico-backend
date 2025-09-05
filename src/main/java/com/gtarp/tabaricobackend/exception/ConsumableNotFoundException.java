package com.gtarp.tabaricobackend.exception;

public class ConsumableNotFoundException extends NotFoundException {
    public ConsumableNotFoundException(int id) { super("Consumable with id " + id + " not found"); }
    public ConsumableNotFoundException(String name) { super("Consumable with the name " + name + " not found"); }
}
