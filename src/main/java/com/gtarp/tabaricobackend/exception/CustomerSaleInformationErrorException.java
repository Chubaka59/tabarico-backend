package com.gtarp.tabaricobackend.exception;

public class CustomerSaleInformationErrorException extends RuntimeException {
    public CustomerSaleInformationErrorException(String username) { super("Customer sale of " + username + " does not match with the price sent by the frontend"); }
}
