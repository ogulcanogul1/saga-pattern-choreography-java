package com.learning.inventory.service.application.event.inventory;

import com.learning.common.event.DomainEvent;

public sealed interface InventoryEvent extends DomainEvent
permits InventoryRestoredEvent , InventoryDeclinedEvent , InventoryReservedEvent{
}
