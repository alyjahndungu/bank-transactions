//package com.camacuchi.banktransactions.config;
//
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.apache.kafka.streams.StreamsConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaStreamsConfiguration;
//import org.springframework.kafka.core.*;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//
//@Configuration
//@EnableKafka
//public class KafkaConfiguration<T> {
//
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//
//
////    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
////    public KafkaStreamsConfiguration kafkaStreamsConfiguration() {
////        Map<String, Object> properties = new HashMap<>();
////        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "orders");
////        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
////        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
////        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
////        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
////        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
////        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////        return new KafkaStreamsConfiguration(properties);
////    }
//
//    @Bean
//    public ConsumerFactory<String, T> consumerFactory() {
//        final Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "demo-kafka");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.camacuchi.banktransactions.serialiers.HeroDeserializer");
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
////   KafkaConsumer<String, T> kafkaConsumer() {
////        Properties props = new Properties();
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
////        props.put(ConsumerConfig.CLIENT_ID_CONFIG, CONSUMER_APP_ID);
////        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
////        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "com.baeldung.kafka.serdes.Deserializer");
////
////        return new KafkaConsumer<>(props);
////    }
//
//    @Bean
//    public ProducerFactory<String, T> producerFactory() {
//        final Map<String, Object> config = new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.camacuchi.banktransactions.serialiers.HeroSerializer");
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//
//    @Bean
//    public KafkaTemplate<String,  T> kafkaTemplate(final ProducerFactory<String, T> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
//
//
//
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//}
