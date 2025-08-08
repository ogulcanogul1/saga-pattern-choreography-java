package com.learning.inventory.service.application.repository;

import com.learning.inventory.service.application.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory , UUID> {

    Inventory findInventoryByProductId(UUID productId);
}
