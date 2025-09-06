package com.gtarp.tabaricobackend.exception;

public class ExporterSaleNotFoundException extends NotFoundException {
    public ExporterSaleNotFoundException(int id) { super("ExporterSale with id " + id + " not found"); }
}
