package com.example.kafka.producer;

import com.example.kafka.payload.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaJsonProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void send(User user) {
        log.info("sending message: {}", user);
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "user").
                build();
        kafkaTemplate.send(message);
    }

}
