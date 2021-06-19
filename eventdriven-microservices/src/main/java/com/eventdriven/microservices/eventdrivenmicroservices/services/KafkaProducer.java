package com.eventdriven.microservices.eventdrivenmicroservices.services;

import java.io.Serializable;

import org.apache.avro.specific.SpecificRecordBase;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    
    void sendMessage(String topicName, K key, V message);
}
