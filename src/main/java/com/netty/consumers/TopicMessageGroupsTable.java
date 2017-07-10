package com.netty.consumers;

import com.netty.domain.Topic;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vamsi on 10/7/17.
 */
@Getter
public class TopicMessageGroupsTable {
    private final Map<Topic, TopicMessageGroups> map = new HashMap<>();

    public void addMessageGroups(Topic topic, TopicMessageGroups messageGroups) {
        this.map.put(topic, messageGroups);
    }

    public TopicMessageGroups getMessageGroups(Topic topic) {
        return map.get(topic);
    }
}
