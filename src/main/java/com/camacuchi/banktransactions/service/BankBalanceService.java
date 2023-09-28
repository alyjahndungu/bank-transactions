package com.camacuchi.banktransactions.service;

import com.camacuchi.banktransactions.models.BankBalance;
import com.camacuchi.banktransactions.topology.BankBalanceTopology;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.HostInfo;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankBalanceService {

//    private final KafkaStreams kafkaStreams;
//    private final HostInfo hostInfo;
//
//    @Autowired
//    public BankBalanceService(KafkaStreams kafkaStreams, HostInfo hostInfo) {
//        this.kafkaStreams = kafkaStreams;
//        this.hostInfo = hostInfo;
//    }
//
//    public BankBalance getBankBalance(Long bankBalanceId) {
//        return getStore().get(bankBalanceId);
//    }
//
//    private ReadOnlyKeyValueStore<Long, BankBalance> getStore() {
//        return kafkaStreams.store(
//                StoreQueryParameters.fromNameAndType(
//                        BankBalanceTopology.BANK_BALANCES_STORE,
//                        QueryableStoreType.keyValueStore()));
//    }
}
