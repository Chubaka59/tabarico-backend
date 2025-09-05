package com.gtarp.tabaricobackend.exception;

public class ProductAlreadyExistException extends ConflictException {
    public ProductAlreadyExistException(String name) {
        super("the product with the name " + name + " already exists");
    }
}
