package com.gtarp.tabaricobackend.repositories;

import com.gtarp.tabaricobackend.entities.Consumable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Integer> {
    Optional<Consumable> findByName(String name);
}
