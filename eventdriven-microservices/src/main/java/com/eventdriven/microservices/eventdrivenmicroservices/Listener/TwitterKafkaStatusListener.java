package com.eventdriven.microservices.eventdrivenmicroservices.Listener;

import com.eventdriven.microservices.eventdrivenmicroservices.Transformer.TwitterStatusToAvroTransformer;
import com.eventdriven.microservices.eventdrivenmicroservices.services.TwitterKafkaProducer;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Slf4j
@Component
public class TwitterKafkaStatusListener extends StatusAdapter
{
    @Autowired
    TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    @Autowired
    private TwitterKafkaProducer twitterKafkaProducer;

    @Override
    public void onStatus(Status status)
    {
        log.info("Twitter status with text {}", status.getText());

        // Extracting desired field from twitter status
        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer. getStatus(status);

       
        // sending our twitter data to kafka
        twitterKafkaProducer.sendMessage("twitter-topic", twitterAvroModel.getUserId(), twitterAvroModel);
        log.info("Twitter-topic is called ************* ");
    }
}
