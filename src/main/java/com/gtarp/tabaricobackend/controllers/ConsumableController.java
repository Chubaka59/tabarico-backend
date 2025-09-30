package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.dto.ConsumableDto;
import com.gtarp.tabaricobackend.entities.Consumable;
import com.gtarp.tabaricobackend.services.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class ConsumableController {
    @Autowired
    private CrudService<Consumable, ConsumableDto> consumableService;

    @GetMapping("/consumables")
    public List<Consumable> getConsumableList() {
        return consumableService.getAll();
    }

    @GetMapping("/consumables/{id}")
    public ResponseEntity<Consumable> getConsumable(@PathVariable Integer id) {
        try {
            Consumable consumable = consumableService.getById(id);
            return new ResponseEntity<>(consumable, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du consommable avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/consumables")
    public ResponseEntity<Consumable> insert(@RequestBody @Validated ConsumableDto consumableDto) {
        try {
            consumableService.insert(consumableDto);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            log.error("Erreur lors de la création du consommable", e);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/consumables/{id}")
    public ResponseEntity<Consumable> delete(@PathVariable Integer id) {
        try {
            consumableService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du consommable avec l'id : {}", id ,e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/consumables/{id}")
    public ResponseEntity<Consumable> update(@RequestBody @Validated ConsumableDto consumableDto, @PathVariable Integer id) {
        try {
            consumableService.update(id, consumableDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erreur lors de la mise à jour du consommable avec l'id : {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
