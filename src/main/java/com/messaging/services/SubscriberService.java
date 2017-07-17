package com.messaging.services;

import com.messaging.domain.MessageGroup;
import com.messaging.domain.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vamsi on 10/7/17.
 */
public class SubscriberService {


    public List<MessageGroup> getUnconsumedMessages(Topic topic,
                                                    String subscriberGroup) {
        return new ArrayList<>();
    }
}
