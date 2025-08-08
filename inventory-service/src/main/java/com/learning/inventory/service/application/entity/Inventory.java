package com.learning.inventory.service.application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity @Getter
@Setter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    private UUID id;
    private UUID productId;
    private int stock;
}
