package com.praveenukkoji.kafkaproducer.kafka.event;

public interface KafkaEvent {
    String getEventType();

    byte[] getPayload();
}
