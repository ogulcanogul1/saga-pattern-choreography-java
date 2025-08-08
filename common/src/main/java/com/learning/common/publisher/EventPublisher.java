package com.learning.common.publisher;

import com.learning.common.payload.Payload;

public interface EventPublisher<T extends Payload> {
    void publish(T event);
    void publish(T event,String key);
}
