package com.example.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaStringProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send( String message) {
        String topic="message";
        log.info("sending message: {}", message);
        kafkaTemplate.send(topic, message);
    }
}

