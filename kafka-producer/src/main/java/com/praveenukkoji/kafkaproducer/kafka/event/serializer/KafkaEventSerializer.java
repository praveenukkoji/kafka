package com.praveenukkoji.kafkaproducer.kafka.event.serializer;

import com.praveenukkoji.kafkaproducer.kafka.event.KafkaEvent;
import com.praveenukkoji.kafkaproducer.kafka.event.model.EventModel;
import org.apache.kafka.common.serialization.Serializer;

import java.util.UUID;

public class KafkaEventSerializer
        implements Serializer<KafkaEvent> {

    @Override
    public byte[] serialize(String topic, KafkaEvent event) {
        if (event == null) {
            return null;
        }

        EventModel eventModel = new EventModel();

        eventModel.setEventType(event.getEventType());
        eventModel.setEventId(UUID.randomUUID().toString());
        eventModel.setTimestamp(System.currentTimeMillis());
        eventModel.setPayload(event.getPayload());

        return eventModel.toString().getBytes();
    }
}
