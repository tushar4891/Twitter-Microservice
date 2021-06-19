package com.eventdriven.microservices.eventdrivenmicroservices.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    
    @Bean
    public NewTopic twitterTopic()
    {
        return TopicBuilder.name("twitter-topic").partitions(3).replicas(3).build();
    }
}
