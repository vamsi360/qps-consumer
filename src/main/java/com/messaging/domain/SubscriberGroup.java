package com.messaging.domain;

import lombok.Getter;

import java.util.List;

/**
 * Created by vamsi on 13/7/17.
 */
@Getter
public class SubscriberGroup {
    private Topic topic;
    private MessageGroup messageGroup;
    private SLevel level;
    private int lastConsumedSeqNo;
    private QType qType;

    public boolean isFullyConsumed() {
        return getUnconsumedMessages(1).size() == 0;
    }

    public List<Message> getUnconsumedMessages(int count) {
        return messageGroup.getMessages(lastConsumedSeqNo, count);
    }

    public enum SLevel {
        L1, L2, L3
    }

    public enum QType {
        main, sidelined, retry_1, retry_2, retry_3
    }
}
