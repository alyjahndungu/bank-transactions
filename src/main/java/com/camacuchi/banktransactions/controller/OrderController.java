package com.camacuchi.banktransactions.controller;

import com.camacuchi.banktransactions.models.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class OrderController {


    private static long orderId = 0;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    LinkedList<Orders> buyOrders = new LinkedList<>(List.of(
            new Orders(++orderId, 1, 1, 100,  "BUY", 1000),
            new Orders(++orderId, 2, 1, 200, "BUY", 1050),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 5, 1, 200, "BUY", 1000),
            new Orders(++orderId, 3, 1, 100, "BUY", 1030),
            new Orders(++orderId, 4, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 52, 1, 200, "BUY", 1000),
            new Orders(++orderId, 36, 1, 100, "BUY", 1030),
            new Orders(++orderId, 42, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 51, 1, 200, "BUY", 1000),
            new Orders(++orderId, 32, 1, 100, "BUY", 1030),
            new Orders(++orderId, 40, 1, 200,  "BUY", 1050),
            new Orders(++orderId, 16, 1, 200, "BUY", 1000),
            new Orders(++orderId, 12, 1, 100,  "BUY", 1050)

    ));

    LinkedList<Orders> sellOrders = new LinkedList<>(List.of(
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050),
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050) ,
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050)  ,
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050) ,
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050)  ,
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050),
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050)  ,
            new Orders(++orderId, 1, 1, 100,  "SELL", 1000),
            new Orders(++orderId, 2, 1, 200, "SELL", 1050),
            new Orders(++orderId, 3, 1, 100, "SELL", 1030),
            new Orders(++orderId, 4, 1, 200,  "SELL", 1050),
            new Orders(++orderId, 5, 1, 200, "SELL", 1000),
            new Orders(++orderId, 11, 1, 100,  "SELL", 1050)
    ));
    @Bean
    public Supplier<Message<Orders>> orderBuySupplier() {
        return () -> {
            if (buyOrders.peek() != null) {
                Message<Orders> o = MessageBuilder
                        .withPayload(buyOrders.peek())
                        .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(buyOrders.poll()).getId())
                        .build();
                logger.info("Order: {}", o.getPayload());
                return o;
            } else {
                return null;
            }
        };
    }

    @Bean
    public Supplier<Message<Orders>> orderSellSupplier() {
        return () -> {
            if (sellOrders.peek() != null) {
                Message<Orders> o = MessageBuilder
                        .withPayload(sellOrders.peek())
                        .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(sellOrders.poll()).getId())
                        .build();
                logger.info("Sell Orders: {}", o.getPayload());
                return o;
            } else {
                return null;
            }
        };
    }

}
