package com.eventdriven.microservices.eventdrivenmicroservices.Listener;

import twitter4j.TwitterException;

public interface StreamRunner {
    
    void start() throws TwitterException;
}
