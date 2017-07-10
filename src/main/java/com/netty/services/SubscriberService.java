package com.netty.services;

import com.netty.consumers.MessageGroupsTable;
import com.netty.domain.MessageGroup;
import com.netty.domain.Topic;

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
