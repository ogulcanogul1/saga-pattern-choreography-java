package com.learning.common.configuration;

public class KafkaTopics {

    public static final String ORDER_CREATED_EVENT = "order-created-event-topic";
    public static final String ORDER_CANCELLED_EVENT = "order-cancelled-event-topic";
    public static final String ORDER_COMPLETED_EVENT = "order-completed-event-topic";

    public static final String INVENTORY_RESERVED_EVENT = "inventory-reserved-event-topic";
    public static final String INVENTORY_RESTORED_EVENT = "inventory-restored-event-topic";
    public static final String INVENTORY_DECLINED_EVENT = "inventory-declined-event-topic";


    public static final String PAYMENT_FAILED_EVENT = "payment-failed-event-topic";
    public static final String PAYMENT_PROCESS_EVENT = "payment-process-event-topic";



}
