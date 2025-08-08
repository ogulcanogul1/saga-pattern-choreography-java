package com.learning.common.processor;

import com.learning.common.payload.Payload;

public interface EventProcessor<T extends Payload, R extends Payload> {
    R process(T event);
}


