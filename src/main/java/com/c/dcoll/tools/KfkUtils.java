package com.c.dcoll.tools;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KfkUtils {

    public static KafkaProducer<String, String> getProducer(){
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.91:9092");//kafka地址，多个地址用逗号分割
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);
        return kafkaProducer;
    }


}
