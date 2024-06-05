package org.example.userauthenticationservice_april.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient
{
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic,String message) {
      kafkaTemplate.send(topic,message);
    }
}

//      producer -----message--------- consumer
//      signup                         EmailService.sendemail
//      userAuthService