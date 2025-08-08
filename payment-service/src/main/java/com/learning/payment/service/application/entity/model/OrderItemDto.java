package com.learning.payment.service.application.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
