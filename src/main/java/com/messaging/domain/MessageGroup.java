package com.messaging.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vamsi on 10/7/17.
 */
@Getter
public class MessageGroup {
    private final String groupId;
    private final List<Message> messages = new ArrayList<>();

    public MessageGroup(String groupId) {
        this.groupId = groupId;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void addMessages(List<Message> messages) {
        this.messages.addAll(messages);
    }

    public List<Message> getMessages(int count) {
        return this.messages
                .stream()
                .limit(count)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Message> getMessages(int startSeqNo, int count) {
        return messages
                .stream()
                .filter(m -> m.getSeqNo() >= startSeqNo)
                .limit(count)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
