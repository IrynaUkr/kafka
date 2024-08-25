package com.example.kafka.consumer;

import com.example.kafka.payload.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "message", groupId = "myGroup")
    public void consumeMessage(String message){
        log.info(" consuming String message from  'MESSAGE' Topic {}",message);
    }

    @KafkaListener(topics = "user", groupId = "myGroup")
    public void consumeUser(User user){
        log.info(" consuming user from  USER Topic {}",user);
    }
}
