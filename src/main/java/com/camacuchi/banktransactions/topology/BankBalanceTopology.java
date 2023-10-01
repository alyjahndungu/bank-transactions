package com.camacuchi.banktransactions.topology;

import com.camacuchi.banktransactions.models.BankBalance;
import com.camacuchi.banktransactions.models.BankTransaction;
import com.camacuchi.banktransactions.models.JsonSerde;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BankBalanceTopology {
    public static final String BANK_TRANSACTIONS = "bank-transactions";
    public static final String BANK_BALANCES = "bank-balances";
    public static final String REJECTED_TRANSACTIONS = "rejected-transactions";
    public static final String BANK_BALANCES_STORE = "bank-balances-store";


    public static Topology buildTopology() {
        Serde<BankTransaction> bankTransactionSerdes = new JsonSerde<>(BankTransaction.class);
        Serde<BankBalance> bankBalanceSerde = new JsonSerde<>(BankBalance.class);
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<Long, BankBalance> bankBalancesStream = streamsBuilder.stream(BANK_TRANSACTIONS,
                        Consumed.with(Serdes.Long(), bankTransactionSerdes))
                .groupByKey()
                .aggregate(BankBalance::new,
                        (key, value, aggregate) -> aggregate.process(value),
                        Materialized.with(Serdes.Long(), bankBalanceSerde))
                .toStream();

        bankBalancesStream
                .to(BANK_BALANCES, Produced.with(Serdes.Long(), bankBalanceSerde));

        bankBalancesStream
                .mapValues((readOnlyKey, value) -> value.getLatestTransaction())
                .filter((key, value) -> value.state == BankTransaction.BankTransactionState.REJECTED)
                .to(REJECTED_TRANSACTIONS, Produced.with(Serdes.Long(), bankTransactionSerdes));

        return streamsBuilder.build();
    }

}
