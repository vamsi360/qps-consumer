package com.netty.consumers;

import com.netty.domain.Message;
import com.netty.domain.MessageGroup;
import com.netty.domain.Topic;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by vamsi on 10/7/17.
 */
@Getter
public class MessageGroupsTable {

    private final Map<String, MessageGroup> messageGroupsMap = new HashMap<>();
    private final Map<Topic, MessageGroup> topicMessageGroupMap = new HashMap<>();
    private final Map<Topic, MessageGroups> globalMap = new HashMap<>();

    public void addMessageGroups(Topic topic, MessageGroups messageGroups) {
        this.globalMap.put(topic, messageGroups);
    }

    public void addMessages(Topic topic, List<Message> messages) {
        messages.forEach(message -> {
            String groupId = message.getGroupId();

            Optional<MessageGroup> oMessageGroup = Optional.ofNullable(messageGroupsMap.get(groupId));

            MessageGroup messageGroup;
            if (!messageGroupsMap.containsKey(groupId)) {
                messageGroup = new MessageGroup(groupId);
            } else {
                messageGroup = messageGroupsMap.get(groupId);
            }

            messageGroup.addMessage(message);
            messageGroupsMap.put(groupId, messageGroup);
            topicMessageGroupMap.put(topic, messageGroup);

            MessageGroups messageGroups;
            if (!globalMap.containsKey(topic)) {
                messageGroups = new MessageGroups(topic);
            } else {
                messageGroups = globalMap.get(topic);
            }

            messageGroups.addMessageGroup(messageGroup);
        });
    }

    public MessageGroups getMessageGroups(Topic topic) {
        return globalMap.get(topic);
    }
}
