package com.camacuchi.banktransactions.producers.controller;


import com.camacuchi.banktransactions.producers.models.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/messages")
public class ProducerController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void publish (@RequestBody MessageRequest messageRequest){
        CompletableFuture<SendResult<String, String>> future =   kafkaTemplate.send("test-topic", messageRequest.message());
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + messageRequest.message() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        messageRequest.message() + "] due to : " + ex.getMessage());
            }
        });

    }
}
