package com.kafka.kafka.service;

import com.kafka.kafka.config.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class service {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    public final static String  topic = "kafka_example1";
    public String KafkaProducer(user user)
    {
        kafkaTemplate.send(topic,user);
        return "Produced successfully";
    }

    public String KafkaConsumer(user user)
    {
        System.out.println("Consumed message : "+user.toString());
        return "Consumed message : "+user.toString();
    }
}
