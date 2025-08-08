package com.learning.inventory.service.application.entity.model;

import lombok.*;

import java.util.UUID;


@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private UUID productId;
    private Integer quantity;
    private Double price;

    public Double getTotal(){
        return price * quantity;
    }

}
