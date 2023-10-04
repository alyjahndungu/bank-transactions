package com.camacuchi.banktransactions.consumer;

import com.camacuchi.banktransactions.models.Orders;
import com.camacuchi.banktransactions.models.Transaction;
import com.camacuchi.banktransactions.models.TransactionTotal;
import com.camacuchi.banktransactions.models.TransactionTotalWithProduct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.state.WindowBytesStoreSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Configuration
public class StockService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static long transactionId = 0;
    @Bean
    public BiConsumer<KStream<Long, Orders>, KStream<Long, Orders>> orders() {
        return (orderBuy, orderSell) -> orderBuy
                .merge(orderSell)
                .peek((k, v) -> {
                    logger.info("New({}): {}", k, v);
//                    logic.add(v);
                });
    }

//    @Bean
//    public BiConsumer<KStream<Long, Orders>, KStream<Long, Orders>> orders() {
//        return (orderBuy, orderSell) -> orderBuy
//                .merge(orderSell)
//                .peek((k, v) -> {
//                    logger.info("New({}): {}", k, v);
////                    logic.add(v);
//                });
//    }
    @Bean
    public BiFunction<KStream<Long, Orders>, KStream<Long, Orders>, KStream<Long, Transaction>> transactions() {
        return (orderBuy, orderSell) -> orderBuy
                .selectKey((k, v) -> v.getProductId())
                .join(orderSell.selectKey((k, v) -> v.getProductId()),
                        this::execute,
                        JoinWindows.of(Duration.ofSeconds(1)),
                        StreamJoined.with(Serdes.Integer(), new JsonSerde<>(Orders.class), new JsonSerde<>(Orders.class)))
                .filterNot((k, v) -> v == null)
                .map((k, v) -> new KeyValue<>(v.getId(), v))
                .peek((k, v) -> logger.info("Completed Transactions Done!!! -> {}", v));
    }


    @Bean
    public Consumer<KStream<Long, Transaction>> total() {
        KeyValueBytesStoreSupplier storeSupplier = Stores.persistentKeyValueStore(
                "all-transactions-store");
        return transactions -> transactions
                .groupBy((k, v) -> v.getStatus(),
                        Grouped.with(Serdes.String(), new JsonSerde<>(Transaction.class)))
                .aggregate(
                        TransactionTotal::new,
                        (k, v, a) -> {
                            a.setCount(a.getCount() + 1);
                            a.setProductCount(a.getProductCount() + v.getAmount());
                            a.setAmount(a.getAmount() + (v.getPrice() * v.getAmount()));
                            return a;
                        },
                        Materialized.<String, TransactionTotal> as(storeSupplier)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(new JsonSerde<>(TransactionTotal.class)))
                .toStream()
                .peek((k, v) -> logger.info("Total: {}", v));
    }

    @Bean
    public BiConsumer<KStream<Long, Transaction>, KStream<Long, Orders>> totalPerProduct() {
        KeyValueBytesStoreSupplier storeSupplier = Stores.persistentKeyValueStore(
                "transactions-per-product-store");
        return (transactions, orders) -> transactions
                .selectKey((k, v) -> v.getSellOrderId())
                .join(orders.selectKey((k, v) -> v.getId()),
                        (t, o) -> new TransactionTotalWithProduct(t, o.getProductId()),
                        JoinWindows.of(Duration.ofSeconds(2)),
                        StreamJoined.with(Serdes.Long(),
                                new JsonSerde<>(Transaction.class),
                                new JsonSerde<>(Orders.class)))
                .groupBy((k, v) -> v.getProductId(),
                        Grouped.with(Serdes.Integer(), new JsonSerde<>(TransactionTotalWithProduct.class)))
                .aggregate(
                        TransactionTotal::new,
                        (k, v, a) -> {
                            a.setCount(a.getCount() + 1);
                            a.setProductCount(a.getProductCount() + v.getTransaction().getAmount());
                            a.setAmount(a.getAmount() + (v.getTransaction().getPrice() * v.getTransaction().getAmount()));
                            return a;
                        },
                        Materialized.<Integer, TransactionTotal> as(storeSupplier)
                                .withKeySerde(Serdes.Integer())
                                .withValueSerde(new JsonSerde<>(TransactionTotal.class)))
                .toStream()
                .peek((k, v) -> logger.info("Total per product({}): {}", k, v));
    }

    @Bean
    public BiConsumer<KStream<Long, Transaction>, KStream<Long, Orders>> latestPerProduct() {
        WindowBytesStoreSupplier storeSupplier = Stores.persistentWindowStore(
                "latest-transactions-per-product-store", Duration.ofSeconds(30), Duration.ofSeconds(30), false);
        return (transactions, orders) -> transactions
                .selectKey((k, v) -> v.getSellOrderId())
                .join(orders.selectKey((k, v) -> v.getId()),
                        (t, o) -> new TransactionTotalWithProduct(t, o.getProductId()),
                        JoinWindows.of(Duration.ofSeconds(10)),
                        StreamJoined.with(Serdes.Long(), new JsonSerde<>(Transaction.class), new JsonSerde<>(Orders.class)))
                .groupBy((k, v) -> v.getProductId(), Grouped.with(Serdes.Integer(), new JsonSerde<>(TransactionTotalWithProduct.class)))
                .windowedBy(TimeWindows.of(Duration.ofSeconds(30)))
                .aggregate(
                        TransactionTotal::new,
                        (k, v, a) -> {
                            a.setCount(a.getCount() + 1);
                            a.setAmount(a.getAmount() + v.getTransaction().getAmount());
                            return a;
                        },
                        Materialized.<Integer, TransactionTotal> as(storeSupplier)
                                .withKeySerde(Serdes.Integer())
                                .withValueSerde(new JsonSerde<>(TransactionTotal.class)))
                .toStream()
                .peek((k, v) -> logger.info("Total per product last 30s({}): {}", k, v));
    }
    private Transaction execute(Orders orderBuy, Orders orderSell) {
        if (orderBuy.getAmount() >= orderSell.getAmount()) {
            int count = Math.min(orderBuy.getProductCount(), orderSell.getProductCount());
//            log.info("Executed: orderBuy={}, orderSell={}", orderBuy.getId(), orderSell.getId());
//            boolean allowed = logic.performUpdate(orderBuy.getId(), orderSell.getId(), count);
//            if (!allowed)
//                return null;
//            else
                return new Transaction(
                        ++transactionId,
                        orderBuy.getId(),
                        orderSell.getId(),
                        Math.min(orderBuy.getProductCount(), orderSell.getProductCount()),
                        (orderBuy.getAmount() + orderSell.getAmount()) / 2,
                        LocalDateTime.now(),
                        "NEW");
        } else {
            return null;
        }
    }


}
