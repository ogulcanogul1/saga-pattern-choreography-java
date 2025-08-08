package com.learning.common.event;

import java.time.Instant;

public interface DomainEvent {
    default Instant getCreatedAt() {
        return Instant.now();
    }
}
