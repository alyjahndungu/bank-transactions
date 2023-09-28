package com.camacuchi.banktransactions;

import com.camacuchi.banktransactions.topology.BankBalanceTopology;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransactionsApplication.class, args);

//		var configuration = KafkaStreamConfiguration.getConfiguration();
//		var topology = BankBalanceTopology.buildTopology();
//		var kafkaStreams = new KafkaStreams(topology, configuration);
//		kafkaStreams.start();

//		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}

}
