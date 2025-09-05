package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.ProductDto;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.exception.ProductNotFoundException;
import com.gtarp.tabaricobackend.repositories.ProductRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstractCrudService<Product, ProductRepository, ProductDto> {

    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }

    @Override
    public Product getById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product insert(ProductDto productDto) {
        return null;
    }
}
