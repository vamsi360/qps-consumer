package com.messaging.consumers;

import com.messaging.domain.Message;
import com.messaging.domain.MessageGroup;
import com.messaging.domain.Topic;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Message> getMessages(Topic topic, String groupId) {
        List<Message> messages;
        if (messageGroupsMap.containsKey(groupId)) {
            messages = messageGroupsMap.get(groupId).getMessages();
        } else {
            messages = new ArrayList<>();
        }
        return messages;
    }

    public MessageGroups getMessageGroups(Topic topic) {
        return globalMap.get(topic);
    }
}
