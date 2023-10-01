package com.camacuchi.banktransactions.service;

import com.camacuchi.banktransactions.models.BankBalance;
import com.camacuchi.banktransactions.topology.BankBalanceTopology;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.HostInfo;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class BankBalanceService {

@Autowired

    public BankBalance getBankBalance(Long bankBalanceId) {
        return getStore().get(bankBalanceId);
    }
        private ReadOnlyKeyValueStore<Long, BankBalance> getStore() {
        return kafkaStreams.store(
                StoreQueryParameters.fromNameAndType(
                        BankBalanceTopology.BANK_BALANCES_STORE,
                        QueryableStoreTypes.keyValueStore()));
    }

}
