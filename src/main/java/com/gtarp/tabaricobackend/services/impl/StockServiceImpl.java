package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.StockDto;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.Stock;
import com.gtarp.tabaricobackend.entities.TypeOfStockMovement;
import com.gtarp.tabaricobackend.repositories.ProductRepository;
import com.gtarp.tabaricobackend.repositories.StockRepository;
import com.gtarp.tabaricobackend.services.ProductService;
import com.gtarp.tabaricobackend.services.StockService;
import com.gtarp.tabaricobackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public void insert(StockDto stockDto, String username) {
        Product product = productService.getById(stockDto.getProductId());

        Stock newStock = new Stock();
        newStock.setDate(LocalDate.now());
        newStock.setTypeOfStockMovement(TypeOfStockMovement.stockModification);
        newStock.setProduct(product);
        newStock.setQuantityMouvement(stockDto.getQuantity());
        newStock.setUser(userService.getByUsername(username));

        product.setStock(product.getStock() + newStock.getQuantityMouvement());
        productRepository.save(product);
        stockRepository.save(newStock);
    }

    @Override
    public List<Stock> getStockDtoListByDate(String date) {
        return stockRepository.getStockListByDate(LocalDate.parse(date));
    }
}
