package com.praveenukkoji.kafkaproducer.kafka.event.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventModel {
    private String eventType;
    private String eventId;
    private long timestamp;
    private byte[] payload;
}
