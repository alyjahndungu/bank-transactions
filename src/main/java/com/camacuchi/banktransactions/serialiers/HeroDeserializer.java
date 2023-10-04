//package com.camacuchi.banktransactions.serialiers;
//
//import com.camacuchi.banktransactions.models.SuperHero;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.errors.SerializationException;
//import org.apache.kafka.common.serialization.Deserializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Map;
//
//@Slf4j
//public class HeroDeserializer implements Deserializer<SuperHero> {
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void configure(Map<String, ?> configs, boolean isKey) {
//    }
//
//    @Override
//    public SuperHero deserialize(String topic, byte[] data) {
//        try {
//            if (data == null){
//                logger.info("Null received at deserializing");
//                return null;
//            }
//           logger.info("Deserializing...");
//            return objectMapper.readValue(new String(data, "UTF-8"), SuperHero.class);
//        } catch (Exception e) {
//            throw new SerializationException("Error when deserializing byte[] to MessageDto");
//        }
//    }
//
//    @Override
//    public void close() {
//    }
//}