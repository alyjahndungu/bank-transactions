//package com.camacuchi.banktransactions.serialiers;
//
//import com.camacuchi.banktransactions.models.SuperHero;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.kafka.common.errors.SerializationException;
//import org.apache.kafka.common.serialization.Serializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//
//public class HeroSerializer implements Serializer<SuperHero> {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//    }
//
//    @Override
//    public byte[] serialize(String topic, SuperHero data) {
//        try {
//            if (data == null){
//              logger.info("Null received at serializing");
//                return null;
//            }
//           logger.info("Serializing...");
//            return objectMapper.writeValueAsBytes(data);
//        } catch (Exception e) {
//            throw new SerializationException("Error when serializing MessageDto to byte[]");
//        }
//    }
//
//    @Override
//    public void close() {
//    }
//}
