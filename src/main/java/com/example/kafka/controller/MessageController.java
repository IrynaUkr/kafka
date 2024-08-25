package com.example.kafka.controller;

import com.example.kafka.payload.User;
import com.example.kafka.producer.KafkaJsonProducer;
import com.example.kafka.producer.KafkaStringProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final KafkaStringProducer kafkaStringProducer;
    private final KafkaJsonProducer kafkaJsonProducer;

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        log.info("Message received: {}", message);
        kafkaStringProducer.send(message);
        return ResponseEntity.ok("message queued successfully " + message);
    }

    @PostMapping("/user")
    public ResponseEntity<String> sendUser(@RequestBody User user) {
        log.info("User received: {}", user.getFirstName());
        kafkaJsonProducer.send(user);
        return ResponseEntity.ok("user queued successfully " + user);
    }

}
