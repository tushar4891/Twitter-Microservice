package com.microservice.kafkaconsumer.Consumer.Impl;

import java.util.List;

import com.microservice.kafkaconsumer.Consumer.KafkaConsumer;
import com.microservices.demo.kafka.avro.model.TwitterAvroModel;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumerImple implements KafkaConsumer<Long,TwitterAvroModel>
{

    @Override
    @KafkaListener(id = "twitterTopicListener", topics="twitter-topic")
    public void receiveMessage(@Payload List<TwitterAvroModel> messages, 
                                @Header (KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                                @Header (KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                                @Header (KafkaHeaders.OFFSET)  List<Long> offset)
                                
    {
        log.info("{} Number of message received with keys {}, Partitions {} and offset {}," +
                                        "Sending it ot elastic: Thread id {} ",
                                        messages.size(),
                                        keys.toString(),
                                        partitions.toString(),
                                        offset.toString(),
                                        Thread.currentThread().getId());
    }
    
}
