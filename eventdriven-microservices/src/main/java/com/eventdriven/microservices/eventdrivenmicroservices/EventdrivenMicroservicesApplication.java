package com.eventdriven.microservices.eventdrivenmicroservices;

import java.util.Arrays;

import com.eventdriven.microservices.eventdrivenmicroservices.Config.TwitterToKafkaConfig;
import com.eventdriven.microservices.eventdrivenmicroservices.Listener.StreamRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class EventdrivenMicroservicesApplication implements CommandLineRunner
{
	@Autowired
	private StreamRunner streamRunner;

	@Autowired
	private TwitterToKafkaConfig twitterToKafkaConfig;

	
	public static void main(String[] args) {
		SpringApplication.run(EventdrivenMicroservicesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		
		log.info("App starts...");
        log.info(Arrays.toString(twitterToKafkaConfig.getTwitterKeywords().toArray(new String[] {})));
		//log.info("Reading twitter words done....");
        log.info(twitterToKafkaConfig.getWelcomeMessage());
        streamRunner.start();
	}

}
