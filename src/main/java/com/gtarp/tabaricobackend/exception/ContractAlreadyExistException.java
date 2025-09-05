package com.gtarp.tabaricobackend.exception;

public class ContractAlreadyExistException extends ConflictException {
    public ContractAlreadyExistException(String company) {
        super("Contract for the company " + company + " already exists");
    }
}
