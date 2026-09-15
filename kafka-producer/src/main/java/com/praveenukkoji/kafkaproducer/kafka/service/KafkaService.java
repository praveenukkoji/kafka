package com.praveenukkoji.kafkaproducer.kafka.service;

import com.praveenukkoji.kafkaproducer.kafka.event.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaService {
    private final KafkaTemplate<String, KafkaEvent> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(KafkaEvent kafkaEvent) {
        kafkaTemplate.send("course-events", kafkaEvent);
        log.info("sent event: {}", kafkaEvent);

        // to see event in terminal
        // list topics
        // kafka-topics --bootstrap-server localhost:9092 --list

        // create topic
        // kafka-topics --bootstrap-server localhost:9092 --create --topic course-events --partitions 3 --replication-factor 1

        // new tab list incoming events
        // kafka-console-consumer --bootstrap-server localhost:9092 --topic course-events --from-beginning
    }
}
