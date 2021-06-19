package com.eventdriven.microservices.eventdrivenmicroservices.Transformer;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;

import org.springframework.stereotype.Component;

import twitter4j.Status;

// Converting twitter data into Avro schema
@Component
public class TwitterStatusToAvroTransformer 
{
    public TwitterAvroModel getStatus(Status status) 
    {
        return TwitterAvroModel
                .newBuilder()
                .setId(status.getId())
                .setUserId(status.getUser().getId())
                .setText(status.getText())
                .setCreatedAt(status.getCreatedAt().getTime())
                .build();
    }
    
}
