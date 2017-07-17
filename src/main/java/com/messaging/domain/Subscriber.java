package com.messaging.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vamsi on 13/7/17.
 */
@Getter
public class Subscriber {

    private final String name;
    private final List<SubscriberGroup> groups = new ArrayList<>();

    public Subscriber(String name) {
        this.name = name;
    }

    public void addSubscriberGroup(SubscriberGroup group) {
        this.groups.add(group);
    }

    public void addSubscriberGroup(List<SubscriberGroup> groups) {
        this.groups.addAll(groups);
    }

    public List<MessageGroup> getUnconsumedMessageGroups(SubscriberGroup.QType qType, int count) {
        return groups.stream()
                .filter(SubscriberGroup::isFullyConsumed)
                .map(SubscriberGroup::getMessageGroup)
                .limit(count)
                .collect(Collectors.toList());
    }

    public List<Message> getUnconsumedMessages(SubscriberGroup.QType qType,
                                               String groupId,
                                               int count) {
        throw new RuntimeException("Not yet implemented. Used getUnconsumedMessageGroups(..) instead");
    }
}
