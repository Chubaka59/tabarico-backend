package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.accounting.CreateExporterSaleDto;
import com.gtarp.tabaricobackend.dto.accounting.ExporterSaleDto;
import com.gtarp.tabaricobackend.entities.User;
import com.gtarp.tabaricobackend.entities.accounting.ExporterSale;
import com.gtarp.tabaricobackend.exception.ExporterSaleNotFoundException;
import com.gtarp.tabaricobackend.repositories.accounting.ExporterSaleRepository;
import com.gtarp.tabaricobackend.services.ExporterSaleService;
import com.gtarp.tabaricobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public ExporterSale insert(CreateExporterSaleDto createExporterSaleDto, String username) {
        User user = userService.getByUsername(username);
        ExporterSale exporterSale = new ExporterSale();
        exporterSale.setDate(LocalDateTime.now());
        exporterSale.setLevel(createExporterSaleDto.getLevel() > 100 ? 100 : createExporterSaleDto.getLevel() );
        exporterSale.setQuantity(createExporterSaleDto.getQuantity());
        exporterSale.setUser(user);
        exporterSale.setEmployeeAmount(calculateExporterEmployeeAmount(createExporterSaleDto));
        //le montant employé * 0.3 pour obtenir le montant entreprise
        exporterSale.setCompanyAmount(exporterSale.getEmployeeAmount().multiply(BigDecimal.valueOf(0.3)).setScale(0, RoundingMode.HALF_UP));
        return exporterSaleRepository.save(exporterSale);
    }

    private BigDecimal calculateExporterEmployeeAmount(CreateExporterSaleDto createExporterSaleDto) {
        int level = createExporterSaleDto.getLevel();
        int quantity = createExporterSaleDto.getQuantity();
        // si le level > 100, il n'y a plus de bonus sur le prix
        if (level > 100) {
            level = 100;
        }
        //36 est le prix d'une cigarette de base. on ajoute ensuite un bonus de 0.3% de 36 en fonction du niveau du vendeur et on multiplie par le nombre de cigarette
        return BigDecimal.valueOf((36+(36*(level*0.3/100)))*quantity).setScale(0, RoundingMode.HALF_UP);
    }
}
