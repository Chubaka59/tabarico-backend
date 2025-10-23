package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.ConsumableDto;
import com.gtarp.tabaricobackend.dto.StockDto;
import com.gtarp.tabaricobackend.dto.ShowStockDto;
import com.gtarp.tabaricobackend.entities.Consumable;
import com.gtarp.tabaricobackend.entities.Product;
import com.gtarp.tabaricobackend.entities.Stock;
import com.gtarp.tabaricobackend.entities.TypeOfStockMovement;
import com.gtarp.tabaricobackend.repositories.ConsumableRepository;
import com.gtarp.tabaricobackend.repositories.ProductRepository;
import com.gtarp.tabaricobackend.repositories.StockRepository;
import com.gtarp.tabaricobackend.services.CrudService;
import com.gtarp.tabaricobackend.services.ProductService;
import com.gtarp.tabaricobackend.services.StockService;
import com.gtarp.tabaricobackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private CrudService<Consumable, ConsumableDto> consumableService;
    @Autowired
    private ConsumableRepository consumableRepository;

    @Transactional
    @Override
    public void insert(StockDto stockDto, String username) {
        Stock newStock = new Stock();
        newStock.setDate(LocalDateTime.now());
        newStock.setTypeOfStockMovement(TypeOfStockMovement.stockModification);
        newStock.setQuantityMouvement(stockDto.getQuantity());
        newStock.setUser(userService.getByUsername(username));

        if (stockDto.getProductId() != null) {
            Product product = productService.getById(stockDto.getProductId());
            newStock.setProduct(product);
            product.setStock(product.getStock() + newStock.getQuantityMouvement());
            productRepository.save(product);
        } else {
            Consumable consumable = consumableService.getById(stockDto.getConsumableId());
            newStock.setConsumable(consumable);
            consumable.setQuantity(consumable.getQuantity() + newStock.getQuantityMouvement());
            consumableRepository.save(consumable);
        }

        stockRepository.save(newStock);
    }

    @Override
    public List<ShowStockDto> getStockDtoListByDate(String date) {
        List<Stock> stockList = stockRepository.getStockByDateBetween(LocalDate.parse(date).atStartOfDay(), LocalDate.parse(date).plusDays(1).atStartOfDay());
        return stockRepository.findAll()
                .stream()
                .map(ShowStockDto::new) // ✅ Appel direct au constructeur
                .toList(); // ou .collect(Collectors.toList()) si Java 8
    }
}
