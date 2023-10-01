package com.camacuchi.banktransactions.consumers.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class KafkaListenerService {

    private CountDownLatch latch = new CountDownLatch(3);

    @KafkaListener(topics = "test-topic",  groupId = "test-groupId")
    public void messageListener(String message){
        System.out.println("Received Message in topic 'test-topic': " + message);

latch.countDown();
    }
}
