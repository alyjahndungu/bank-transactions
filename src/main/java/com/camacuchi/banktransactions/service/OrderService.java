//package com.camacuchi.banktransactions.service;
//
//import com.camacuchi.banktransactions.models.Orders;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Random;
//import java.util.function.Supplier;
//
//@Service
//public class OrderService {
//
//    private static long orderId = 0;
//    private static final Random r = new Random();
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//
//    LinkedList<Orders> buyOrders = new LinkedList<>(List.of(
//            new Orders(++orderId, 1, 1, 100,  "BUY", 1000),
//            new Orders(++orderId, 2, 1, 200, "BUY", 1050),
//            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
//            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
//            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
//            new Orders(++orderId, 11, 1, 100,  "BUY", 1050)
//    ));
//
//    LinkedList<Orders> sellOrders = new LinkedList<>(List.of(
//            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
//            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
//            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
//            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
//            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
//            new Orders(++orderId, 11, 1, 100,  "SELL", 1050)
//    ));
//
//    @Bean
//    public Supplier<Message<Orders>> orderBuySupplier() {
//        return () -> {
//            if (buyOrders.peek() != null) {
//                Message<Orders> o = MessageBuilder
//                        .withPayload(buyOrders.peek())
//                        .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(buyOrders.poll()).getId())
//                        .build();
//                logger.info("Order: {}", o.getPayload());
//                return o;
//            } else {
//                return null;
//            }
//        };
//    }
//
//}
