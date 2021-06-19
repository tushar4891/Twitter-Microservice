package com.eventdriven.microservices.eventdrivenmicroservices.Config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "twitter-to-kafka-services")
@Configuration
public class TwitterToKafkaConfig 
{
    private List<String>twitterKeywords;
    private String welcomeMessage;
}
