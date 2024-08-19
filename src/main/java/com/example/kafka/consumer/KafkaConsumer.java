package com.example.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "orderTopic", groupId = "myGroup")
    public void consumeMessage(String message){
        log.info(" consuming message from  order Topic {}",message);
    }
}
