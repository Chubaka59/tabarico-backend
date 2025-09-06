package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.exception.ExporterSaleNotFoundException;
import com.gtarp.tabaricobackend.repositories.accounting.ExporterSaleRepository;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ExporterSaleServiceImpl implements ExporterSaleService {
    @Autowired
    private ExporterSaleRepository exporterSaleRepository;
    @Autowired
    private UserService userService;

    public List<ExporterSaleDto> findAllByUserForCurrentWeek(String username) {
        User user = userService.getByUsername(username);

        LocalDate today = LocalDate.now();
        LocalDateTime startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).atTime(4, 0, 0, 0);
        LocalDateTime endOfWeek = startOfWeek.plusWeeks(1).withHour(3).withMinute(59).withSecond(59).withNano(999999999);

        return exporterSaleRepository.findAllByUserAndDateBetween(user, startOfWeek, endOfWeek)
                .stream()
                .map(ExporterSaleDto::new)
                .toList();
    }

    public List<ExporterSale> findAllByUser(String username) {
        User user = userService.getByUsername(username);
        return exporterSaleRepository.findAllByUser(user);
    }

    private ExporterSale findById(int id) {
        return exporterSaleRepository.findById(id).orElseThrow(() -> new ExporterSaleNotFoundException(id));
    }

    public void delete(int id) {
        ExporterSale exporterSale = findById(id);
        exporterSaleRepository.delete(exporterSale);
    }
}
