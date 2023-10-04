//package com.camacuchi.banktransactions.consumer;
//
//import com.camacuchi.banktransactions.models.SuperHero;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Component
//public class KafkaListenerService {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    @KafkaListener(topics = "test-topic",  groupId = "test-groupId")
//    public void messageListener(String message){
//        logger.info("Received Message in topic 'test-topic': " + message);
//
//    }
//
//    @KafkaListener(topics = {"superHeroTopic"},  groupId = "group_id")
//    public void consumeSuperHero(   SuperHero superHero ) {
//        superHero.getCities().forEach(cities -> logger.info("Message received " + cities));
//    }
//
//}
