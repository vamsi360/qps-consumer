package com.messaging.domain;

import lombok.Getter;

/**
 * Created by vamsi on 13/7/17.
 */
@Getter
public class Queue {

    private final Topic topic;
    private final Subscriber subscriber;

    public Queue(Topic topic,
                 Subscriber subscriber) {
        this.topic = topic;
        this.subscriber = subscriber;
    }

}
