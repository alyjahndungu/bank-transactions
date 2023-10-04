//package com.camacuchi.banktransactions.controller;
//
//
//import com.camacuchi.banktransactions.models.MessageRequest;
//import com.camacuchi.banktransactions.models.SuperHero;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/v1/messages")
//public class ProducerController {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @Autowired
//    private KafkaTemplate<String, SuperHero> superHeroKafkaTemplate;
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    @PostMapping
//    public void publish (@RequestBody MessageRequest messageRequest){
//        CompletableFuture<SendResult<String, String>> future =   kafkaTemplate.send("test-topic", messageRequest.message());
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                System.out.println("Sent message=[" + messageRequest.message() +
//                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
//            } else {
//                System.out.println("Unable to send message=[" +
//                        messageRequest.message() + "] due to : " + ex.getMessage());
//            }
//        });
//    }
//
//    @GetMapping(value = "super-hero" )
//    public Map<String, Object> sendHeros(SuperHero superHero) {
//        superHeroKafkaTemplate.send("superHeroTopic", superHero);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "Successfully publisher Super Hero..!");
//        map.put("payload", superHero);
//
//        return map;
//    }
//
//    @GetMapping(value = "super-hero" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Map<String, Object> sendMessages(SuperHero superHero) {
//        Flux<SuperHero> superHeroTopic = Flux.defer(() -> {
//            logger.info("Client subscribed to 'send.data' channel. Sending messages to Kafka...");
//            return Flux.interval(Duration.ofSeconds(1))
//                    .map(tick -> {
//                       logger.info("Sending message to Kafka: {}", superHero);
//                        superHeroKafkaTemplate.send("superHeroTopic", superHero);
//                        return superHero;
//                    });
//        });
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", "Successfully publisher Super Hero..!");
//        map.put("payload", superHeroTopic);
//
//        return map;
//    }
//
//    @MessageMapping("/sendHeros")
//    @SendTo("/topic/group")
//    public MessageRequest broadcastGroupMessage(@Payload MessageRequest messageRequest) {
//        return messageRequest;
//    }
//}
