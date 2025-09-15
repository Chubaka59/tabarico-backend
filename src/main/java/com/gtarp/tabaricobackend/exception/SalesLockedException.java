package com.gtarp.tabaricobackend.exception;

public class SalesLockedException extends RuntimeException {
    public SalesLockedException() {
        super("Les ventes sont actuellement bloquées!");
    }
}
