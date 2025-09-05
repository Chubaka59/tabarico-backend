package com.gtarp.tabaricobackend.services.impl;

import com.gtarp.tabaricobackend.dto.ConsumableDto;
import com.gtarp.tabaricobackend.entities.Consumable;
import com.gtarp.tabaricobackend.exception.ConsumableAlreadyExistException;
import com.gtarp.tabaricobackend.exception.ConsumableNotFoundException;
import com.gtarp.tabaricobackend.repositories.ConsumableRepository;
import com.gtarp.tabaricobackend.services.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumableServiceImpl extends AbstractCrudService<Consumable, ConsumableRepository, ConsumableDto> {

    public ConsumableServiceImpl(ConsumableRepository repository) {
        super(repository);
    }

    @Override
    public Consumable getById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ConsumableNotFoundException(id));
    }

    @Override
    public Consumable insert(ConsumableDto consumableDto) {
        Optional<Consumable> consumable = repository.findByName(consumableDto.getName());
        if (consumable.isPresent()) {
            throw new ConsumableAlreadyExistException(consumableDto.getName());
        }
        Consumable newConsumable = new Consumable(consumableDto);
        return this.repository.save(newConsumable);
    }
}
