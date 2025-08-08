package com.learning.common.payload.inventory.model;

import com.learning.common.event.InventoryStatus;
import com.learning.common.payload.order.model.OrderPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class InventoryPayload {
    private OrderPayload order;
    private InventoryStatus inventoryStatus;

}
