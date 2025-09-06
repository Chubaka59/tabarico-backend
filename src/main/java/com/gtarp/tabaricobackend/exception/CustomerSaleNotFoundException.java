package com.gtarp.tabaricobackend.exception;

public class CustomerSaleNotFoundException extends NotFoundException {
    public CustomerSaleNotFoundException(int id) { super("CustomerSale with id " + id + " not found"); }
}
