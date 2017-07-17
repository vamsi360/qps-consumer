package com.messaging.domain;

import lombok.Getter;

/**
 * Created by vamsi on 10/7/17.
 */
@Getter
public class Topic {
    private final String name;
    private final boolean grouped;

    public Topic(String name,
                 boolean grouped) {
        this.name = name;
        this.grouped = grouped;
    }
}
