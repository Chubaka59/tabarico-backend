package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.ProductDto;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {
    @Autowired
    private CrudService<Product, ProductDto> productService;

    @GetMapping("/products")
    public List<Product> getUserList() {
        return productService.getAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        try {
            Product product = productService.getById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
