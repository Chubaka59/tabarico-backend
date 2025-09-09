package com.gtarp.tabaricobackend.services;

import com.gtarp.tabaricobackend.dto.ProductDto;
import com.gtarp.tabaricobackend.entities.Product;

public interface ProductService extends CrudService<Product, ProductDto> {
    /**
     * find a product by its name
     * @param name the name of the product
     * @return a product
     */
    Product findProductByName(String name);
}
