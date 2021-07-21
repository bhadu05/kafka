package com.kafka.kafka;

import com.kafka.kafka.config.user;
import com.kafka.kafka.service.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    service service;

/*    @PostMapping("/kafka/producer")
    String Producer(@RequestParam String message)
    {
        return service.KafkaProducer(message);
    }*/
    @Autowired
    KafkaTemplate<String ,Object> kafkaTemplate;

    user userFromTopic=null;
    List<String >messages=new ArrayList<>();
    @PostMapping("/kafka/produce/user")
    String produceUser(@RequestBody user user)
    {
       kafkaTemplate.send("kafka_example1",user);
       return "Produced successfully";

    }
    @PostMapping("/kafka/produce/string/{message}")
    String produceString(@PathVariable String message)
    {
        kafkaTemplate.send("kafka_example1",message);
        return "Produced successfully";
    }
    @GetMapping("/kafka/consumer/user")
    public user consumeUser()
    {
        return userFromTopic;
    }
    @GetMapping("/kafka/consumer/string")
    public List<String >cousumeString()
    {
        return messages;
    }
/*    @KafkaListener(groupId = "javatechie-1", topics = "kafka_example1", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        return messages;
    }*/

    @KafkaListener(groupId = "javatechie-2", topics = "kafka_example1", containerFactory = "userKafkaListenerContainerFactory")
    public user getJsonMsgFromTopic(user user) {
        userFromTopic = user;
        return userFromTopic;
    }
}
