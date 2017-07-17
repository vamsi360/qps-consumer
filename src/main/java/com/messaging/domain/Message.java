package com.messaging.domain;

import lombok.Getter;

/**
 * Created by vamsi on 10/7/17.
 */
@Getter
public class Message {

    private Integer seqNo;
    private String messageId;
    private String groupId;
    private byte[] payload;

}
