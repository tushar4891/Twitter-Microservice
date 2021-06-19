package com.eventdriven.microservices.eventdrivenmicroservices.services;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TwitterKafkaProducer implements KafkaProducer<Long,TwitterAvroModel>
{
    @Autowired
    private KafkaTemplate<Long,TwitterAvroModel> kafkaTemplate;

    @Override
    public void sendMessage(String topicName, Long key, TwitterAvroModel message) 
    {
        log.info("Sending message {}, to topic {} ", message, topicName);
      // ListenableFuture<SendResult<Long,TwitterAvroModel>> kafkaResultFuture = 
        log.info("message is sending*********");
         //kafkaTemplate.send(topicName, key, message);
        kafkaTemplate.send("twitter-topic", key, message)
        .addCallback(new ListenableFutureCallback<SendResult<Long,TwitterAvroModel>>()
        {

            @Override
            public void onSuccess(SendResult<Long, TwitterAvroModel> result) 
            {
                RecordMetadata metadata = result.getRecordMetadata();
                log.debug("Received new metadata. Topic : {} , Partition:{}, Offset{}, Timestamp {}, at time {}",
                            
                            metadata.topic(),
                            metadata.partition(),
                            metadata.offset(),
                            metadata.timestamp(),
                            System.nanoTime());
                log.info("SENT*****");
            }
            
            @Override
            public void onFailure(Throwable throwable) {
                
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }
            
        });

        log.info("1 message sent successfully **************");
    }
    

}
