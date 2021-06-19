package com.eventdriven.microservices.eventdrivenmicroservices.Listener;

import javax.annotation.PreDestroy;

import com.eventdriven.microservices.eventdrivenmicroservices.Config.TwitterToKafkaConfig;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Component
@Slf4j
public class StreamRunnerImpl implements StreamRunner {
    
    private  TwitterKafkaStatusListener twitterKafkaStatusListener;
    private  TwitterToKafkaConfig twitterToKafkaConfig;

    public StreamRunnerImpl(TwitterKafkaStatusListener twitterKafkaStatusListener, 
    TwitterToKafkaConfig twitterToKafkaConfig) {
        this.twitterKafkaStatusListener = twitterKafkaStatusListener;
        this.twitterToKafkaConfig = twitterToKafkaConfig;
    }

    private TwitterStream twitterStream;

    @Override
    public void start() throws TwitterException 
    {
            //TwitterStreamFactory() is a factory class for TwitterFactory.
            //TwitterStreamFactory().getInstance() Returns a instance associated with the configuration
            //bound to this factory.
            twitterStream = new TwitterStreamFactory().getInstance();

            twitterStream.addListener(twitterKafkaStatusListener);
            addFilter();
    }

    private void addFilter() 
    {
        String[] keywords = twitterToKafkaConfig.getTwitterKeywords().toArray(new String[0]);
        //FilterQuery filterQuery = new FilterQuery(keywords);
        //twitterStream.filter(filterQuery);
        //log.info("Started filtering twitter stream for keywords {}", Arrays.toString(keywords));
        FilterQuery filterQuery = new FilterQuery();
        filterQuery.track(keywords);
        twitterStream.filter(filterQuery);
    }

    @PreDestroy
    public void shutDown()
    {
        if(twitterStream != null)
        {
            log.info("Closing twitter stream....!");
            twitterStream.shutdown();
        }
    }
}
